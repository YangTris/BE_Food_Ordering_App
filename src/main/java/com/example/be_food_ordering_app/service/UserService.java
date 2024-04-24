package com.example.be_food_ordering_app.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.example.be_food_ordering_app.entity.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserService {

    public List<User> getUsers(HttpServletRequest req) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        String searchString = req.getParameter("query");
        ApiFuture<QuerySnapshot> future = db.collection("users")
                .orderBy("phone")
                .startAt(searchString)
                .endAt(searchString + '~')
                .get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<User> users = documents.stream().map(document -> document.toObject(User.class)).toList();

        return users;
    }

    // check regex for phone number
    public boolean checkPhoneNumber(String phone) {
        String pattern = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
        return phone.matches(pattern);
    }

    public boolean checkPassword(String password) {
        String pattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        return password.matches(pattern);
    }

    public boolean checkGmail(String gmail) {
        String pattern = "^[a-zA-Z0-9+_.-]+@gmail.com$";
        return gmail.matches(pattern);
    }

    public boolean checkUniquePhoneNumber(String phone) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("users").whereEqualTo("phone", phone).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        return documents.isEmpty();
    }

    public String saveCustomer(User user) throws InterruptedException, ExecutionException {
        if (!checkPhoneNumber(user.getPhone())) {
            return "Invalid phone number";
        }
        if (!checkUniquePhoneNumber(user.getPhone())) {
            return "Phone number already exists";
        }
        if (!checkPassword(user.getPassword())) {
            return "Password must contain at least 8 characters, 1 uppercase, 1 lowercase, 1 number and 1 special character";
        }
        if (!checkGmail(user.getEmail())) {
            return "Email must coitain @gmail.com";
        }
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("users").document();
        user.setUserId(docRef.getId());
        if (user.getUserImg() == null) {
            user.setUserImg(
                    "https://firebasestorage.googleapis.com/v0/b/food-ordering-app-63b1a.appspot.com/o/users%2F415d35b1-3162-424e-b05d-5dba53c65696?alt=media&token=9e452244-0983-4c92-b34a-831510fb67f3");
        }

        ApiFuture<WriteResult> result = docRef.set(user);
        result.get();
        return "Created account " + user.getPhone();
    }

    // login user by phoneNumber and password
    public String loginUser(String phoneNumber, String password) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("users").whereEqualTo("phone", phoneNumber)
                .whereEqualTo("password", password)
                .get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        if (documents.isEmpty()) {
            return null;
        }
        return documents.get(0).getId();
    }

    public User getUserDetails(String id) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("users").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return document.toObject(User.class);
        } else {
            return null;
        }
    }

    public String updateUser(String id, User user) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("users").document(id);
        user.setUserId(docRef.getId());
        ApiFuture<WriteResult> result = docRef.set(user);
        result.get();
        return "Updated user with ID: " + docRef.getId();
    }

    public String deleteUser(String id) {
        Firestore db = FirestoreClient.getFirestore();
        db.collection("users").document(id).delete();

        return "User with ID: " + id + " has been deleted";
    }
}

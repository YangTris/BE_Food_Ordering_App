package com.example.be_food_ordering_app.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.example.be_food_ordering_app.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class UserService {

    public List<User> getAllUsers() throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("users").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<User> users = documents.stream().map(document -> document.toObject(User.class)).toList();

        return users;
    }

    public String saveCustomer(User user) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("users").whereEqualTo("phone", user.getPhone()).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        if (documents.isEmpty()) {
            DocumentReference docRef = db.collection("users").document();
            user.setUserId(docRef.getId());
            ApiFuture<WriteResult> result = docRef.set(user);
            return "Saved user with ID: " + docRef.getId();
        } else {
            return "Phone number already exists";
        }
    }

    // login user by phoneNumber and password
    public String loginUser(User user) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("users").whereEqualTo("phone", user.getPhone())
                .whereEqualTo("password", user.getPassword()).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        if (documents.isEmpty()) {
            return "Wrong phone number or password";
        } else {
            return "Welcome " + documents.get(0).get("name") + " !";
        }
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

        return "Updated user with ID: " + docRef.getId();
    }

    public String deleteUser(String id) {
        Firestore db = FirestoreClient.getFirestore();
        db.collection("users").document(id).delete();

        return "User with ID: " + id + " has been deleted";
    }
}

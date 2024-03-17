package com.example.be_food_ordering_app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FirebaseService {
    private final Firestore db = FirestoreClient.getFirestore();

    // public String
    public Food createFoodinFireBase(Food food) {
        DocumentReference docRef = db.collection("foods").document();
        Map<String, Object> data = new HashMap<>();

        String id = UUID.randomUUID().toString();

        data.put("id", id);
        data.put("name", food.getName());
        data.put("description", food.getDescription());
        docRef.set(data);
        ApiFuture<WriteResult> result = docRef.set(data);

        // return result.get().getUpdateTime().toString();
        return food;
        // System.out.println("Update time : " + result.get().getUpdateTime());
        // CollectionReference foodsRef = db.collection("foods");
        // String id = UUID.randomUUID().toString();
        // food.setId(id);
        // foodsRef.document(id).set(food);
        // return food;
    }

    public List<Food> getAllFoodinFireBase() {
        List<Food> foods = new ArrayList<>();
        QuerySnapshot querySnapshot = null;
        try {
            querySnapshot = db.collection("foods").get().get();
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

        for (QueryDocumentSnapshot document : documents) {
            Food food = document.toObject(Food.class);
            foods.add(food);
        }

        return foods;
    }

    public Food getFoodFromFireBase(String id) {
        CollectionReference foodsRef = db.collection("foods");
        try {
            return foodsRef.document(id).get().get().toObject(Food.class);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null; // or handle the exception accordingly
        }
    }

    public Food updateFoodInFireBase(String id, Food food) {
        CollectionReference foodsRef = db.collection("foods");
        foodsRef.document(id).set(food);
        return food;
    }

    public void deleteFoodFromFireBase(String id) {
        CollectionReference foodsRef = db.collection("foods");
        foodsRef.document(id).delete();
    }
}

package com.example.be_food_ordering_app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.example.be_food_ordering_app.entity.Food;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Query;

@Service
public class FoodService {

    public String saveFood(Food food) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("foods").document();
        food.setId(docRef.getId());
        ApiFuture<WriteResult> result = docRef.set(food);
        ApiFuture<WriteResult> writeResult = docRef.update("timestamp", FieldValue.serverTimestamp());
        return result.get().getUpdateTime().toString();
    }

    public List<Food> getAllFoods() throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        // query all documents order by timestamp desc
        ApiFuture<QuerySnapshot> future = db.collection("foods").orderBy("timestamp", Query.Direction.DESCENDING).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Food> foods = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            foods.add(document.toObject(Food.class));
        }
        return foods;
    }

    public void deleteFood(String id) {
        Firestore db = FirestoreClient.getFirestore();
        db.collection("foods").document(id).delete();
    }

    public String updateFood(String id, Food food) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("foods").document(id);
        food.setId(docRef.getId());
        ApiFuture<WriteResult> result = docRef.set(food);
        ApiFuture<WriteResult> writeResult = docRef.update("timestamp", FieldValue.serverTimestamp());

        return result.get().getUpdateTime().toString();
    }

    public Food getFoodDetails(String id) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        return db.collection("foods").document(id).get().get().toObject(Food.class);
    }

}

package com.example.be_food_ordering_app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.example.be_food_ordering_app.entity.Food;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import jakarta.servlet.http.HttpServletRequest;

import com.google.cloud.firestore.Query;

@Service
public class FoodService {

    public String createFood(Food food) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("foods").document();
        food.setId(docRef.getId());
        if (food.getImgURL() == null)
            food.setImgURL(
                    "https://firebasestorage.googleapis.com/v0/b/food-ordering-app-63b1a.appspot.com/o/category_foods.png?alt=media&token=0258300e-a4c3-4ee5-8d2a-8c5383547eb3");
        ApiFuture<WriteResult> result = docRef.set(food);
        ApiFuture<WriteResult> writeResult = docRef.update("timestamp", FieldValue.serverTimestamp());
        writeResult.get();
        return result.get().getUpdateTime().toString();
    }

    public List<Food> getFoods(HttpServletRequest req) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        String searchString = req.getParameter("query");
        String cap = "";
        if (searchString != "") {
            cap = searchString.substring(0, 1).toUpperCase() + searchString.substring(1);
        }
        // 0 Asc, 1 Desc
        // String sortType = req.getParameter("sortType");
        // double priceRange = Double.parseDouble(req.getParameter("priceRange"));

        ApiFuture<QuerySnapshot> future = db.collection("foods")
                // .whereGreaterThan("price", priceRange)
                // .whereLessThan("price", priceRange + 500)
                // .orderBy("price")
                // .orderBy("price", sortType.equals("Asc") ? Query.Direction.ASCENDING :
                // Query.Direction.DESCENDING)
                .orderBy("name")
                .startAt(cap)
                .endAt(cap + '~')
                .get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Food> foods = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            foods.add(document.toObject(Food.class));
        }
        return foods;
    }

    public String deleteFood(String id) {
        Firestore db = FirestoreClient.getFirestore();
        db.collection("foods").document(id).delete();

        return "Document with ID " + id + " has been deleted!";
    }

    public String updateFood(String id, Food food) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("foods").document(id);
        food.setId(docRef.getId());
        if (food.getImgURL() == null)
            food.setImgURL(
                    "https://firebasestorage.googleapis.com/v0/b/food-ordering-app-63b1a.appspot.com/o/category_foods.png?alt=media&token=0258300e-a4c3-4ee5-8d2a-8c5383547eb3");
        ApiFuture<WriteResult> result = docRef.set(food);
        ApiFuture<WriteResult> writeResult = docRef.update("timestamp", FieldValue.serverTimestamp());
        writeResult.get();
        return result.get().getUpdateTime().toString();
    }

    public Food getFoodDetails(String id) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        return db.collection("foods").document(id).get().get().toObject(Food.class);
    }

    // sort, filter
    public List<Food> sortFoods(boolean sortType, double priceRange) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        Query query = db.collection("foods").orderBy("price",
                sortType ? Query.Direction.ASCENDING : Query.Direction.DESCENDING);
        ApiFuture<QuerySnapshot> future = query.whereLessThan("price", priceRange).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Food> foods = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            foods.add(document.toObject(Food.class));
        }
        return foods;
    }

}

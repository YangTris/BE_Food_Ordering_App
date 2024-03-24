package com.example.be_food_ordering_app.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.example.be_food_ordering_app.model.Cart;
import com.example.be_food_ordering_app.model.Food;
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
public class CartService {

    // check if food exists in cart
    public boolean checkFoodExists(String foodId, String userId) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("carts").whereEqualTo("foodId", foodId)
                .whereEqualTo("userId", userId).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        return !documents.isEmpty();
    }

    // Add Food to cart by id
    public String addFoodToCart(Cart cart)
            throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();

        // set cartId == userId

        // cart.setQuantity(1);
        // if (!checkFoodExists(foodId, userId)) {
        // cart.setQuantity(1);
        // } else {
        // cart.setQuantity(cart.getQuantity() + 1);
        // }
        // cart.setPrice(cart.getQuantity() * food.getPrice());

        DocumentReference docRef = db.collection("carts").document();
        cart.setCartId(docRef.getId());
        ApiFuture<WriteResult> result = docRef.set(cart);

        return result.get().getUpdateTime().toString();
        // return "Added " + food.getName() + " to " + user.getName() + " cart";
    }

    // delete cart item by cartId
    public void deleteAllCartItem(String id) {
        Firestore db = FirestoreClient.getFirestore();
        db.collection("carts").document(id).delete();
    }

    // get total price of cart items by cartId
    public double getTotalPrice(String id) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("carts").whereEqualTo("cartId", id).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        double totalPrice = 0;
        for (QueryDocumentSnapshot document : documents) {
            totalPrice += document.toObject(Cart.class).getPrice();
        }
        return totalPrice;
    }

    // get total quantity of cart items by cartId
    public int getTotalQuantity(String id) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("carts").whereEqualTo("cartId", id).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        int totalQuantity = 0;
        for (QueryDocumentSnapshot document : documents) {
            totalQuantity += document.toObject(Cart.class).getQuantity();
        }
        return totalQuantity;
    }

    // delete cart item by foodId and userId
    public void deleteCartItemByFoodId(String userId, String foodId) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("carts").whereEqualTo("foodId", foodId)
                .whereEqualTo("userId", userId).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            db.collection("carts").document(document.getId()).delete();
        }
    }

    // get all cart items
    public Cart getAllCartItem(String id) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("carts").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return document.toObject(Cart.class);
        } else {
            return null;
        }
    }

}

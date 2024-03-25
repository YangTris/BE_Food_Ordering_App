package com.example.be_food_ordering_app.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.example.be_food_ordering_app.entity.CartItem;
import com.example.be_food_ordering_app.entity.Food;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class CartService {

    public CartItem getCartId(String userId, String foodId) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("carts").whereEqualTo("userId", userId)
                .whereEqualTo("foodId", foodId).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        return documents.isEmpty() ? null : documents.get(0).toObject(CartItem.class);
    }

    // get all cart items
    public List<CartItem> getAllCartItem(String id) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("carts").whereEqualTo("userId", id).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        return documents.stream().map(document -> document.toObject(CartItem.class)).toList();
    }

    // Add Food to cart by id
    public String addFoodToCart(CartItem cart)
            throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("carts").document();
        DocumentReference food = db.collection("foods").document(cart.getFoodId());
        cart.setCartId(docRef.getId());

        cart.setPrice(food.get().get().toObject(Food.class).getPrice() * cart.getQuantity());

        ApiFuture<WriteResult> result = docRef.set(cart);

        return result.get().getUpdateTime().toString();
    }

    public String updateCarByFoodId(CartItem cart)
            throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("carts").document(cart.getCartId());
        DocumentReference food = db.collection("foods").document(cart.getFoodId());
        cart.setQuantity(cart.getQuantity() + docRef.get().get().toObject(CartItem.class).getQuantity());
        cart.setPrice(food.get().get().toObject(Food.class).getPrice() * cart.getQuantity());
        ApiFuture<WriteResult> result = docRef.set(cart);
        return result.get().getUpdateTime().toString();
    }

    // get total price of cart items by cartId
    public double getTotalPrice(String id) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("carts").whereEqualTo("cartId", id).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        double totalPrice = 0;
        for (QueryDocumentSnapshot document : documents) {
            totalPrice += document.toObject(CartItem.class).getPrice();
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
            totalQuantity += document.toObject(CartItem.class).getQuantity();
        }
        return totalQuantity;
    }

    public void deleteCartItem(String cartId) {
        Firestore db = FirestoreClient.getFirestore();
        db.collection("carts").document(cartId).delete();
    }

    public void clearCartByUserId(String userId) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("carts").whereEqualTo("userId", userId).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            db.collection("carts").document(document.getId()).delete();
        }
    }

}

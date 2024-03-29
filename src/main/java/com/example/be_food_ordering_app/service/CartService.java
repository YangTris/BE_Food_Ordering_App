package com.example.be_food_ordering_app.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.example.be_food_ordering_app.entity.Cart;
import com.example.be_food_ordering_app.entity.CartItem;

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

    // get all cart items
    public List<Cart> getAllCartItem(String id) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("carts").whereEqualTo("userId", id).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        return documents.stream().map(document -> document.toObject(Cart.class)).toList();
    }

    public String createCart(String cartId, CartItem newItem) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("carts").document(cartId);
        Cart cart = new Cart();
        cart.setCartId(cartId);
        cart.setUserId(cartId);
        cart.setCartItems(List.of(newItem));
        cart.getCartItems().forEach(item -> item.setTotal(item.getQuantity() * item.getPrice()));

        double totalPrice = calculateTotalPrice(cart);
        cart.setTotalPrice(totalPrice);

        ApiFuture<WriteResult> result = docRef.set(cart);
        return "Item added to cart successfully at: " + result.get().getUpdateTime();

    }

    public String addToCart(String cartId, CartItem newItem) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();

        DocumentReference cartRef = db.collection("carts").document(cartId);

        ApiFuture<DocumentSnapshot> future = cartRef.get();
        DocumentSnapshot document = future.get();

        Cart existingCart = document.toObject(Cart.class);

        List<CartItem> cartItems = existingCart.getCartItems();
        cartItems.add(newItem);
        cartItems.forEach(item -> item.setTotal(item.getQuantity() * item.getPrice()));

        double totalPrice = calculateTotalPrice(existingCart);
        existingCart.setTotalPrice(totalPrice);

        existingCart.setCartItems(cartItems);

        ApiFuture<WriteResult> writeResult = cartRef.set(existingCart);
        return "Item added to cart successfully at: " + writeResult.get().getUpdateTime();
    }

    public List<CartItem> checkCartItemExists(String userId) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("carts").whereEqualTo("userId", userId).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        return documents.isEmpty() ? null : documents.get(0).toObject(Cart.class).getCartItems();
    }

    private double calculateTotalPrice(Cart cart) {
        double totalPrice = 0.0;
        for (CartItem item : cart.getCartItems()) {
            totalPrice += item.getTotal();
        }
        return totalPrice;
    }

    // get total quantity of cart items by userId
    public String getTotalQuantity(String id) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("carts").whereEqualTo("userId", id).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        int totalQuantity = 0;
        for (QueryDocumentSnapshot document : documents) {
            Cart cart = document.toObject(Cart.class);
            totalQuantity += cart.getCartItems().size();
        }
        return "Total quantity of cart items: " + totalQuantity;
    }

    public String deleteCartItem(String cartId) {
        Firestore db = FirestoreClient.getFirestore();
        db.collection("carts").document(cartId).delete();

        return "Document with ID " + cartId + " has been deleted!";
    }

    public String clearCartByUserId(String userId) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("carts").whereEqualTo("userId", userId).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            db.collection("carts").document(document.getId()).delete();
        }

        return "Cart of user with ID " + userId + " has been cleared!";
    }

}

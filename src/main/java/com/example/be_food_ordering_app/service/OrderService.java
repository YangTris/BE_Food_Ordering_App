package com.example.be_food_ordering_app.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.example.be_food_ordering_app.entity.Cart;
import com.example.be_food_ordering_app.entity.Order;
import com.example.be_food_ordering_app.entity.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class OrderService {
    public Order createOrder(Cart cart) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("orders").document();

        Order order = new Order();
        order.setOrderId(docRef.getId());
        order.setUserId(cart.getUserId());
        order.setCart(cart);
        order.setOrderTotal(cart.getTotalPrice());
        order.setOrderStatus("Pending");
        order.setPaymentMethod("VNPay");
        order.setPaymentStatus("Pending");
        order.setMerchantAdress("273 An Dương Vương, Phường 3, Quận 5, Thành phố Hồ Chí Minh");

        ApiFuture<QuerySnapshot> future = db.collection("users").whereEqualTo("userId", cart.getUserId()).get();
        order.setUserPhone(future.get().toObjects(User.class).get(0).getPhone());
        order.setUserAddress(future.get().toObjects(User.class).get(0).getAddress());
        order.setUserName(future.get().toObjects(User.class).get(0).getName());

        ApiFuture<WriteResult> result = docRef.set(order);
        result.get();
        ApiFuture<WriteResult> writeResult = docRef.update("orderDate", FieldValue.serverTimestamp());
        writeResult.get();

        return order;
    }

    public String updateOrderStatus(String orderId, String shipperId, String orderStatus) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("orders").document(orderId);
        ApiFuture<QuerySnapshot> future = db.collection("users").whereEqualTo("userId", shipperId).get();
        String shipperName = future.get().toObjects(User.class).get(0).getName();
        ApiFuture<WriteResult> writeResult = docRef.update("shipperId", shipperId, "shipperName", shipperName,"orderStatus", orderStatus);
        
        return "Update Shipper for Order with ID: " + orderId + " at: " + writeResult.get().getUpdateTime().toString();
    }

    public String updatePaymentStatus(String orderId, String paymentStatus)
            throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("orders").document(orderId);
        ApiFuture<WriteResult> writeResult = docRef.update("paymentStatus", paymentStatus);
        return "Update Payment status order with ID: " + orderId + " at: "
                + writeResult.get().getUpdateTime().toString();
    }

    public Order getOrder(String orderId) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("orders").whereEqualTo("orderId", orderId).get();
        return future.get().toObjects(Order.class).get(0);
    }

    // get all orders by userId
    public List<Order> getOrderByUserId(String userId) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("orders").whereEqualTo("userId", userId).get();
        return future.get().toObjects(Order.class);
    }

    // list all order
    public List<Order> getAllOrder() throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("orders").get();
        return future.get().toObjects(Order.class);
    }

}

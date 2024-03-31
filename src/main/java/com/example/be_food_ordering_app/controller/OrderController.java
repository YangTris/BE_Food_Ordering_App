package com.example.be_food_ordering_app.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.be_food_ordering_app.entity.Cart;
import com.example.be_food_ordering_app.entity.Order;
import com.example.be_food_ordering_app.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderId)
            throws InterruptedException, ExecutionException {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @GetMapping("/order/orderhistory/{userId}")
    public ResponseEntity<List<Order>> getAllOrder(@PathVariable String userId)
            throws InterruptedException, ExecutionException {
        return ResponseEntity.ok(orderService.getOrderByUserId(userId));
    }

    @PostMapping("/order")
    public ResponseEntity<String> createOrder(@RequestBody Cart cart) throws InterruptedException, ExecutionException {
        return ResponseEntity.ok(orderService.createOrder(cart));
    }

    @PutMapping("/order")
    public ResponseEntity<String> updateShipperId(@RequestBody Order order)
            throws InterruptedException, ExecutionException {
        return ResponseEntity.ok(orderService.updateShipperId(order.getOrderId(), order.getShipperId()));
    }

}

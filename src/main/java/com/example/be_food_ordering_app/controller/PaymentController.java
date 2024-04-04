package com.example.be_food_ordering_app.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.be_food_ordering_app.service.PaymentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<String> createPayment(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        return ResponseEntity.ok(paymentService.createPayment(req, resp));
    }
}

package com.kems.Kems.controller;


import com.kems.Kems.entity.Order;
import com.kems.Kems.entity.OrderRequest;
import com.kems.Kems.entity.User;
import com.kems.Kems.repository.UserRepository;
import com.kems.Kems.security.JwtAuthFilter;
import com.kems.Kems.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public CompletableFuture<ResponseEntity<String>> placeOrder(@RequestBody OrderRequest orderRequest) {
        log.info("🔹 Received Order Request: {}", orderRequest);

        if (orderRequest.getUserId() == null) {
            log.error("🚨 User ID is missing in the request!");
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("User ID is required"));
        }

        Optional<User> userOpt = userRepository.findById(orderRequest.getUserId());
        if (userOpt.isEmpty()) {
            log.error("🚨 User not found with ID: {}", orderRequest.getUserId());
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("User not found"));
        }

        Order order = new Order();
        order.setProductName(orderRequest.getProductName());
        order.setQuantity(orderRequest.getQuantity());
        order.setPrice(orderRequest.getPrice());
        order.setStatus("PENDING");
        order.setUser(userOpt.get());

        orderService.processOrder(order);
        return CompletableFuture.completedFuture(ResponseEntity.ok("Order is being processed"));
    }


}

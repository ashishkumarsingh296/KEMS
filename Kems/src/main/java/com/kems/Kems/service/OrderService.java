package com.kems.Kems.service;

import com.kems.Kems.entity.Order;
import com.kems.Kems.repository.OrderRepository;
import com.kems.Kems.security.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.kafka.core.KafkaTemplate;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    @Async
    public void processOrder(Order order) {
        try {
            log.info("🔹 Processing order: {}", order);
            Thread.sleep(5000); // Simulate processing delay
            
            // Check if Order is NULL
            if (order == null) {
                log.error("🚨 Order object is NULL!");
                return;
            }
            if (order.getUser() == null) {
                log.error("🚨 Order has no user assigned!");
                return;
            }

            order.setStatus("COMPLETED");
            orderRepository.save(order);
            log.info("✅ Order {} completed", order.getId());

            // Publish event to Kafka
            kafkaTemplate.send("order-events", "Order Completed: " + order.getId());
            log.info("📢 Published event to Kafka");

        } catch (Exception e) {
            log.error("❌ Error processing order: {}", e.getMessage(), e);
        }
    }
}

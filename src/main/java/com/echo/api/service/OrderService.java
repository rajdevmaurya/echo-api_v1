package com.echo.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.echo.api.model.Order;
import com.echo.api.model.OrderItem;
import com.echo.api.repository.OrderRepository;
import com.echo.api.rest.dto.OrderRequestDTO;
import com.echo.api.rest.dto.OrderResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    
    private final OrderRepository orderRepository;
    
    /**
     * Creates a new order in the system
     * 
     * @param orderRequest The order details from the client
     * @param userId The ID of the user placing the order
     * @return OrderResponseDTO with confirmation details
     */
    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequest, String userId) {
        log.info("Creating order for user: {}", userId);
        
        // Generate order ID if not provided
        if (orderRequest.getOrderId() == null || orderRequest.getOrderId().isEmpty()) {
            orderRequest.setOrderId("ORD" + UUID.randomUUID().toString().substring(0, 8));
        }
        
        // Set order date if not provided
        if (orderRequest.getDate() == null) {
            orderRequest.setDate(LocalDateTime.now());
        }
        
        // Set initial status if not provided
        if (orderRequest.getStatus() == null || orderRequest.getStatus().isEmpty()) {
            orderRequest.setStatus("received");
        }
        
        // Map DTO to entity
        Order order = mapToEntity(orderRequest, userId);
        
        // Save the order
        Order savedOrder = orderRepository.save(order);
        
        // Create and return response
        return OrderResponseDTO.builder()
                .orderId(savedOrder.getOrderId())
                .status("success")
                .message("Order placed successfully")
                .timestamp(LocalDateTime.now())
                .build();
    }
    
    /**
     * Maps the order request DTO to an Order entity
     */
    private Order mapToEntity(OrderRequestDTO dto, String userId) {
        // Create order entity using builder
        Order order = Order.builder()
                .orderId(dto.getOrderId())
                .userId(userId)
                .totalAmount(dto.getTotalAmount())
                .status(dto.getStatus())
                .orderDate(dto.getDate())
                .shippingName(dto.getShipping().getName())
                .shippingEmail(dto.getShipping().getEmail())
                .shippingAddress(dto.getShipping().getAddress())
                .shippingCity(dto.getShipping().getCity())
                .shippingState(dto.getShipping().getState())
                .shippingZipCode(dto.getShipping().getZipCode())
                .paymentMethod(dto.getPayment().getMethod())
                .paymentStatus(dto.getPayment().getStatus())
                .items(new ArrayList<>())
                .build();
        
        // Create and add order items
        List<OrderItem> items = dto.getItems().stream()
                .map(itemDto -> OrderItem.builder()
                        .productId(itemDto.getId())
                        .title(itemDto.getTitle())
                        .quantity(itemDto.getQuantity())
                        .price(itemDto.getPrice())
                        .company(itemDto.getCompany())
                        .brand(itemDto.getBrand())
                        .order(order)
                        .build())
                .toList();
        
        order.getItems().addAll(items);
        
        return order;
    }
}
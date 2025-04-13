package com.echo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.echo.api.model.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    /**
     * Find an order by its unique order ID
     */
    Optional<Order> findByOrderId(String orderId);
    
    /**
     * Find all orders for a specific user
     */
    List<Order> findByUserId(String userId);
    
    /**
     * Find all orders with a specific status
     */
    List<Order> findByStatus(String status);
    
    /**
     * Find orders for a specific user with a specific status
     */
    List<Order> findByUserIdAndStatus(String userId, String status);
}
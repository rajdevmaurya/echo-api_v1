package com.echo.api.model;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "booking_orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "items")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String orderId;
    
    @Column(nullable = false)
    private String userId;
    
    @Column(nullable = false)
    private BigDecimal totalAmount;
    
    @Column(nullable = false)
    private String status;
    
    @Column(nullable = false)
    private LocalDateTime orderDate;
    
    // Shipping information
    @Column(nullable = false)
    private String shippingName;
    
    @Column(nullable = false)
    private String shippingEmail;
    
    @Column(nullable = false)
    private String shippingAddress;
    
    @Column(nullable = false)
    private String shippingCity;
    
    @Column(nullable = false)
    private String shippingState;
    
    @Column(nullable = false)
    private String shippingZipCode;
    
    // Payment information
    @Column(nullable = false)
    private String paymentMethod;
    
    private String paymentStatus;
    
    @Builder.Default
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();
}


package com.echo.api.rest.dto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    
    private String orderId;
    
    @NotEmpty(message = "Order must contain at least one item")
    private List<OrderItemDTO> items;
    
    @NotNull(message = "Shipping information is required")
    private ShippingInfoDTO shipping;
    
    @NotNull(message = "Payment information is required")
    private PaymentInfoDTO payment;
    
    @NotNull(message = "Total amount is required")
    private BigDecimal totalAmount;
    
    private String status;
    
    private LocalDateTime date;
}


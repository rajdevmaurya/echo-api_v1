package com.echo.api.rest.dto;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    
    private String orderId;
    private String status;
    private String message;
    private LocalDateTime timestamp;
    
    public OrderResponseDTO(String orderId, String status, String message) {
        this.orderId = orderId;
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
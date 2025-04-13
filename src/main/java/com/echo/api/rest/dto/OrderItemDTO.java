package com.echo.api.rest.dto;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    
    private String id;
    
    private String title;
    
    private Integer quantity;
    
    private BigDecimal price;
    
    private String company;
    
    private String brand;
}


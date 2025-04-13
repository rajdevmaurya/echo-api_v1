package com.echo.api.rest.dto;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShippingInfoDTO {
    
    private String name;
    
    private String email;
    
    private String address;
    
    private String city;
    
    private String state;
    
    @Pattern(regexp = "\\d{6}", message = "PIN code must be 6 digits")
    private String zipCode;
}


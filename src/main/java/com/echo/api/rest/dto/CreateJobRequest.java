package com.echo.api.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateJobRequest {

    @Schema(example = "Software Developer")
    @NotBlank
    private String title;

    @Schema(example = "Google")
    @NotBlank
    private String company;

    @Schema(example = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Google_2015_logo.svg/500px-Google_2015_logo.svg.png")
    private String logoUrl;

    @Schema(example = "Software Developer with more than 5 years experience")
    @NotBlank
    private String description;
    
    private String lookupType;
    
    private String featureDescription;
    
    private String brand;
    
    private Long price;
    
    private int qty;
}

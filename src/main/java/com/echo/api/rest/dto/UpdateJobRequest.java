package com.echo.api.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateJobRequest {

    @Schema(example = "Echo Health care")
    private String title;

    @Schema(example = "Echo Health care")
    private String company;

    @Schema(example = "Echo Health care")
    private String logoUrl;

    @Schema(example = "Echo Health care")
    private String description;
    
    private String lookupType;
    
    private String featureDescription;
    
    private String brand;
}

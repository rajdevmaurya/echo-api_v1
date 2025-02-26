package com.echo.api.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SearchRequest {

    @NotBlank
    @Schema(title = "text to be searched", example = "Medical Devices")
    private String text;
}
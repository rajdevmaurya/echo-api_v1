package com.echo.api.rest.dto;

public record OrderResponse(Long id, String title, String company, String logoUrl, String description,
                          String createDate) {
}

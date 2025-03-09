package com.echo.api.rest.dto;

public record JobResponse(Long id, String title, String company, String logoUrl, String description,
                          String createDate,String lookupType,String brand,String featureDescription) {
}


package com.echo.api.rest.dto;
import java.time.Instant;

import com.echo.api.model.OrderServiceEntity;

public record OrderDto(Long id, String description, UserDto user, Instant createdAt) {

    public record UserDto(String username) {
    }

    public static OrderDto from(OrderServiceEntity order) {
        UserDto userDto = new UserDto(order.getUser().getUsername());
        return new OrderDto(order.getId(), order.getDescription(), userDto, order.getCreateDate());
    }
}
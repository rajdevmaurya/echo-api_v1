package com.echo.api.mapper;

import com.echo.api.model.OrderServiceEntity;
import com.echo.api.rest.dto.CreateOrderRequest;
import com.echo.api.rest.dto.OrderResponse;
import com.echo.api.rest.dto.UpdateOrderRequest;

public interface OrderMapper {

	OrderResponse toOrderResponse(OrderServiceEntity order);

	OrderServiceEntity toOrder(CreateOrderRequest createOrderRequest);

    void updateOrderFromRequest(UpdateOrderRequest updateJobRequest, OrderServiceEntity order);
}
package com.echo.api.mapper;

import org.springframework.stereotype.Service;

import com.echo.api.model.OrderServiceEntity;
import com.echo.api.rest.dto.CreateOrderRequest;
import com.echo.api.rest.dto.OrderResponse;
import com.echo.api.rest.dto.UpdateOrderRequest;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
@Service
public class OrderMapperImpl implements OrderMapper {
	 private static final DateTimeFormatter DATE_TIME_FORMATTER =
	            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());
    @Override
    public OrderResponse toOrderResponse(OrderServiceEntity order) {
        if (order == null) {
            return null;
        }
       // return new JobResponse(job.getId(), job.getTitle(), job.getCompany(), job.getLogoUrl(), job.getDescription(), job.getCreateDate());
        return  new OrderResponse(
        		order.getId(),
        		order.getTitle(),
        		order.getCompany(),
        		order.getLogoUrl(),
        		order.getDescription(),
                DATE_TIME_FORMATTER.format(order.getCreateDate()));
    }

    @Override
    public OrderServiceEntity toOrder(CreateOrderRequest createOrderRequest) {
        if (createOrderRequest == null) {
            return null;
        }
        return new OrderServiceEntity(createOrderRequest.getTitle(), createOrderRequest.getCompany(), createOrderRequest.getLogoUrl(), createOrderRequest.getDescription());
    }

    @Override
    public void updateOrderFromRequest(UpdateOrderRequest updateOrderRequest, OrderServiceEntity order) {
        if (updateOrderRequest == null) {
            return;
        }

        if (updateOrderRequest.getTitle() != null) {
        	order.setTitle(updateOrderRequest.getTitle());
        }
        if (updateOrderRequest.getCompany() != null) {
        	order.setCompany(updateOrderRequest.getCompany());
        }
        if (updateOrderRequest.getLogoUrl() != null) {
        	order.setLogoUrl(updateOrderRequest.getLogoUrl());
        }
        if (updateOrderRequest.getDescription() != null) {
        	order.setDescription(updateOrderRequest.getDescription());
        }
    }
}

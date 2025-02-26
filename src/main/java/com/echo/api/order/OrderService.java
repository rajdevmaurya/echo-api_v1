package com.echo.api.order;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.echo.api.model.OrderServiceEntity;

public interface OrderService {

	List<OrderServiceEntity> getOrders();

	List<OrderServiceEntity> getOrdersContainingText(String text);

	OrderServiceEntity validateAndGetOrder(Long id);

	OrderServiceEntity saveOrder(OrderServiceEntity order);

	void deleteOrder(OrderServiceEntity order);

	Page<OrderServiceEntity> getOrdersByPage(Pageable pageable);

	OrderServiceEntity validateAndGetOrderById(Long id);
	 
	    
}

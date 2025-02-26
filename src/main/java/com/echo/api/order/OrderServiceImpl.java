package com.echo.api.order;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.echo.api.model.OrderServiceEntity;
import com.echo.api.repository.OrderServiceRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

	private final OrderServiceRepository orderRepository;

	@Override
	public List<OrderServiceEntity> getOrders() {
		return orderRepository.findAllByOrderByCreateDateDesc();
	}

	@Override
	public List<OrderServiceEntity> getOrdersContainingText(String text) {
		return null;//orderRepository.findByIdContainingOrDescriptionContainingIgnoreCaseOrderByCreatedAt(text, text);
	}

	@Override
	public OrderServiceEntity validateAndGetOrder(Long id) {
		return orderRepository.findById(id)
				.orElseThrow(() -> new OrderNotFoundException(String.format("Order with id %s not found", id)));
	}

	@Override
	public OrderServiceEntity saveOrder(OrderServiceEntity order) {
		return orderRepository.save(order);
	}

	@Override
	public void deleteOrder(OrderServiceEntity order) {
		orderRepository.delete(order);
	}

	@Override
	public Page<OrderServiceEntity> getOrdersByPage(Pageable pageable) {
		return orderRepository.findAll(pageable);
	}

	@Override
	public OrderServiceEntity validateAndGetOrderById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}

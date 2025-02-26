package com.echo.api.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.echo.api.mapper.OrderMapper;
import com.echo.api.model.JobServiceEntity;
import com.echo.api.model.OrderServiceEntity;
import com.echo.api.model.User;
import com.echo.api.order.OrderService;
import com.echo.api.rest.dto.CreateJobRequest;
import com.echo.api.rest.dto.CreateOrderRequest;
import com.echo.api.rest.dto.JobResponse;
import com.echo.api.rest.dto.OrderResponse;
import com.echo.api.rest.dto.OrderDto;
import com.echo.api.rest.dto.SearchRequest;
import com.echo.api.rest.dto.UpdateJobRequest;
import com.echo.api.rest.dto.UpdateOrderRequest;
import com.echo.api.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import static com.echo.api.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderMapper orderMapper;
    private final OrderService orderService;
    
    private final UserService userService;

   // @Operation(
    //        summary = "Get jobs with pagination"
    //        ,security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)}
    //        )
    @GetMapping
    public Page<OrderResponse> getOrders(@ParameterObject @PageableDefault(sort = {"createDate"}, direction = Direction.DESC) Pageable pageable,Principal principal) {
        //log.info("Request to get a page of jobs (offset = {}, pageSize = {}) made by {}",  pageable.getOffset(), pageable.getPageSize(), principal.getName());
    	//Pageable pageable = PageRequest.of(0, 10);
        return orderService.getOrdersByPage(pageable).map(orderMapper::toOrderResponse);
    }

    @GetMapping("myorder/{userId}")
    public Page<OrderResponse> getOrdersByUserId(@PathVariable Long userId,@ParameterObject @PageableDefault(sort = {"createDate"}, direction = Direction.DESC) Pageable pageable,Principal principal) {
        //log.info("Request to get a page of jobs (offset = {}, pageSize = {}) made by {}",  pageable.getOffset(), pageable.getPageSize(), principal.getName());
    	//Pageable pageable = PageRequest.of(0, 10);
        return orderService.getOrdersByPage(pageable).map(orderMapper::toOrderResponse);
    }
      
    @Operation(
            summary = "Create a Order"
            , security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)}
            )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderResponse createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest, Principal principal) {
        OrderServiceEntity order = orderMapper.toOrder(createOrderRequest);
        order = orderService.saveOrder(order);
        return orderMapper.toOrderResponse(order);
    }

    @Operation(
            summary = "Delete a job"
            ,security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)}
            )
    @DeleteMapping("/{id}")
    public OrderResponse deleteOrder(@PathVariable Long id, Principal principal) {
       // log.info("Request to delete a job with id {} made by {}", id, principal.getName());
    	OrderServiceEntity order = orderService.validateAndGetOrder(id);
        //orderService.deleteJob(order);
        return orderMapper.toOrderResponse(order);
    }

    @Operation(
            summary = "Update a job"
            ,security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)}
            )
    @PutMapping("/{id}")
    public OrderResponse updateOrder(@PathVariable Long id,
                                 @Valid @RequestBody UpdateOrderRequest updateOrderRequest, Principal principal) {
        log.info("Request to update a job with id {} made by {}", id, principal.getName());
        OrderServiceEntity order = orderService.validateAndGetOrder(id);
        orderMapper.updateOrderFromRequest(updateOrderRequest, order);
        orderService.saveOrder(order);
        return orderMapper.toOrderResponse(order);
    }

    @Operation(
            summary = "Search for jobs"
            ,security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)}
            )
    @PutMapping("/search")
    public Page<OrderServiceEntity> searchOrders(@Valid @RequestBody SearchRequest searchRequest,
                                @ParameterObject @PageableDefault(sort = {"createDate"}, direction = Direction.DESC) Pageable pageable,
                                Principal principal) {
       // log.info("Request to search a job with text {} made by {}", searchRequest.getText(), principal.getName());
    	//Page<OrderServiceEntity> data= orderService.search(searchRequest.getText(), pageable);
        return null;//data;
    }
    @Operation(
            summary = "Create a Order"
            , security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)}
            )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/jobRequest")
    public OrderResponse createOrderRequest(@Valid @RequestBody CreateOrderRequest createOrderRequest, Principal principal) {
        //log.info("Request to create a job made by {}", principal.getName());
        User user = userService.validateAndGetUserByUsername(principal.getName());
        createOrderRequest.setDescription(createOrderRequest.getDescription());
        OrderServiceEntity order = new OrderServiceEntity();
        OrderServiceEntity orderRequest = orderMapper.toOrder(createOrderRequest);
        orderRequest.setUser(user);
        order = orderService.saveOrder(orderRequest);
        return orderMapper.toOrderResponse(order);
    }
    
    @Operation(
            summary = "Get a job by id",
            security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)}
            )
    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable Long id, Principal principal) {
       // log.info("Request to get a job with id {} made by {}", id, principal.getName());
    	OrderServiceEntity order = orderService.validateAndGetOrderById(id);
        return orderMapper.toOrderResponse(order);
    }
    
}

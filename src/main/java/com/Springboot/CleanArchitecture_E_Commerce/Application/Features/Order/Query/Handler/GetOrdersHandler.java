package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Order.Query.Handler;

import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Order.Query.Model.OrderItemResponse;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Order.Query.Model.OrderResponse;
import com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetOrdersHandler {
    private final OrderRepository orderRepository;

    public GetOrdersHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderResponse> handle(Long userId) {
        return orderRepository.findByUserId(userId).stream().map(order -> {
            OrderResponse response = new OrderResponse();
            response.setOrderId(order.getId());
            response.setTotalPrice(order.getTotalPrice());
            response.setStatus(order.getStatus());
            response.setItems(order.getOrderItems().stream().map(item -> {
                OrderItemResponse itemResponse = new OrderItemResponse();
                itemResponse.setProductId(item.getProduct().getId());
                itemResponse.setProductName(item.getProduct().getName());
                itemResponse.setQuantity(item.getQuantity());
                itemResponse.setPrice(item.getPrice());
                return itemResponse;
            }).collect(Collectors.toList()));
            return response;
        }).collect(Collectors.toList());
    }
}
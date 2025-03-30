package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Order.Command.Handler;

import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Order.Command.Model.PlaceOrderCommand;
import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.*;
import com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceOrderHandler {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public PlaceOrderHandler(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public String handle(PlaceOrderCommand command) {
        User user = userRepository.findById(command.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<OrderItem> orderItems = command.getItems().stream().map(item -> {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            return new OrderItem(null, product, item.getQuantity(), product.getPrice().intValue() * item.getQuantity());
        }).collect(Collectors.toList());

        double totalPrice = orderItems.stream().mapToDouble(OrderItem::getPrice).sum();
        Order order = new Order(user, orderItems, totalPrice);
        orderItems.forEach(item -> item.setOrder(order));

        orderRepository.save(order);
        return "Order placed successfully!";
    }
}


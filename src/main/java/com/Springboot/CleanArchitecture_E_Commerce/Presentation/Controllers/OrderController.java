package com.Springboot.CleanArchitecture_E_Commerce.Presentation.Controllers;


import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Order.Command.Handler.PlaceOrderHandler;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Order.Command.Model.PlaceOrderCommand;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Order.Query.Handler.GetOrdersHandler;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Order.Query.Model.OrderResponse;
import com.Springboot.CleanArchitecture_E_Commerce.Presentation.ResponseSchema.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final PlaceOrderHandler placeOrderHandler;
    private final GetOrdersHandler getOrdersHandler;

    public OrderController(PlaceOrderHandler placeOrderHandler, GetOrdersHandler getOrdersHandler) {
        this.placeOrderHandler = placeOrderHandler;
        this.getOrdersHandler = getOrdersHandler;
    }

    @PostMapping("/place")
    public ResponseEntity<ApiResponse<String>> placeOrder(@RequestBody PlaceOrderCommand command) {
        return ResponseEntity.ok(ApiResponse.success("Order placed successfully",placeOrderHandler.handle(command)));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getOrders(@PathVariable Long userId) {
        return ResponseEntity.ok(ApiResponse.success("Orders retrieved successfully",getOrdersHandler.handle(userId)));
    }
}


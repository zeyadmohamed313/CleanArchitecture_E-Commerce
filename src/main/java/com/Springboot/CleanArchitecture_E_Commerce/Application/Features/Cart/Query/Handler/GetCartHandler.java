package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Cart.Query.Handler;

import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Cart.Query.Model.CartItemResponse;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Cart.Query.Model.GetCartQuery;
import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.Cart;
import com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetCartHandler {

    private final CartRepository cartRepository;

    public GetCartHandler(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<CartItemResponse> handle(GetCartQuery query) {
        Cart cart = cartRepository.findByUser_Id(query.getUserId()).orElseThrow(() -> new RuntimeException("Cart not found"));
        return cart.getCartItems().stream()
                .map(item -> new CartItemResponse(item.getId(), item.getProduct().getId(), item.getProduct().getName(), item.getQuantity()))
                .collect(Collectors.toList());
    }
}
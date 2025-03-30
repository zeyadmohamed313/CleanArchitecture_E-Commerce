package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Cart.Command.Handler;

import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Cart.Command.Model.RemoveFromCartCommand;
import com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories.CartItemRepository;
import org.springframework.stereotype.Service;

@Service
public class RemoveFromCartHandler {

    private final CartItemRepository cartItemRepository;

    public RemoveFromCartHandler(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public String handle(RemoveFromCartCommand command) {
        cartItemRepository.deleteById(command.getCartItemId());
        return "Product removed from cart successfully!";
    }
}
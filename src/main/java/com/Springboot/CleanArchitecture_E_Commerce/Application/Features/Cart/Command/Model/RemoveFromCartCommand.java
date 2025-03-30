package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Cart.Command.Model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoveFromCartCommand {

    @NotNull(message = "Cart Item ID is required")
    private Long cartItemId;

    public @NotNull(message = "Cart Item ID is required") Long getCartItemId() {
        return cartItemId;
    }

    public RemoveFromCartCommand(Long cartItemId) {
        this.cartItemId = cartItemId;
    }
}
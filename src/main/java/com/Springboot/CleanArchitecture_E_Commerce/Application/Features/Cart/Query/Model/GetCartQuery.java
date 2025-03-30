package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Cart.Query.Model;

import jakarta.validation.constraints.NotNull;

public class GetCartQuery {
    @NotNull(message = "User ID is required")
    private Long userId;

    public @NotNull(message = "User ID is required") Long getUserId() {
        return userId;
    }

    public GetCartQuery(Long userId) {
        this.userId = userId;
    }
}

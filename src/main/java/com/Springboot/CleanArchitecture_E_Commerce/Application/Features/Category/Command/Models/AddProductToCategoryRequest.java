package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Category.Command.Models;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductToCategoryRequest {

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    public @NotNull(message = "Category ID is required") Long getCategoryId() {
        return categoryId;
    }

    public @NotNull(message = "Product ID is required") Long getProductId() {
        return productId;
    }

    @NotNull(message = "Product ID is required")
    private Long productId;
}
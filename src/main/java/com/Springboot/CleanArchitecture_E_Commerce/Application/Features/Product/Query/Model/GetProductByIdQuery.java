package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Product.Query.Model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class GetProductByIdQuery {
    @NotNull(message = "Product ID cannot be null")

    private Long id;

    public GetProductByIdQuery(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
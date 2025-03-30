package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Product.Command.Models;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteProductCommand {

    @NotNull(message = "Product ID cannot be null")
    private Long id;

    public DeleteProductCommand(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
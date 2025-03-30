package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Category.Command.Models;

import jakarta.validation.constraints.NotNull;

public class DeleteCategoryCommand {
    public @NotNull(message = "Category ID is required") Long getId() {
        return id;
    }

    @NotNull(message = "Category ID is required")
    private Long id;

    public DeleteCategoryCommand(Long id) {
        this.id = id;
    }
}

package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Category.Command.Models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateCategoryCommand {
    public @NotNull(message = "Category ID is required") Long getId() {
        return id;
    }

    public @NotBlank(message = "Category name is required") String getName() {
        return name;
    }

    @NotNull(message = "Category ID is required")
    private Long id;

    public void setId(@NotNull(message = "Category ID is required") Long id) {
        this.id = id;
    }

    public void setName(@NotBlank(message = "Category name is required") String name) {
        this.name = name;
    }

    @NotBlank(message = "Category name is required")
    private String name;
}

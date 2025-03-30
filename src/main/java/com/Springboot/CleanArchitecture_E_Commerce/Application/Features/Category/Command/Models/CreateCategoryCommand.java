package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Category.Command.Models;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryCommand {

    public @NotBlank(message = "Category name is required") String getName() {
        return name;
    }

    @NotBlank(message = "Category name is required")
    private String name;
}

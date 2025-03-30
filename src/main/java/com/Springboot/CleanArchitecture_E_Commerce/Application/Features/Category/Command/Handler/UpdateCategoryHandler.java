package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Category.Command.Handler;

import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Category.Command.Models.UpdateCategoryCommand;
import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.Category;
import com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateCategoryHandler {

    private final CategoryRepository categoryRepository;

    public UpdateCategoryHandler(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category handle(UpdateCategoryCommand command) {
        Optional<Category> existingCategory = categoryRepository.findById(command.getId());

        if (existingCategory.isEmpty()) {
            throw new IllegalArgumentException("Category not found");
        }

        Category category = existingCategory.get();
        category.setName(command.getName());

        return categoryRepository.save(category);
    }
}
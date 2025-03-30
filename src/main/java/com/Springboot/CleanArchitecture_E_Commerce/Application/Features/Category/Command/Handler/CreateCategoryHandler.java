package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Category.Command.Handler;

import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Category.Command.Models.CreateCategoryCommand;
import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.Category;
import com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCategoryHandler {

    private final CategoryRepository categoryRepository;

    public CreateCategoryHandler(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category handle(CreateCategoryCommand command) {
        if (categoryRepository.findByName(command.getName())!=null) {
            throw new IllegalArgumentException("Category already exists");
        }

        Category category = new Category();
        category.setName(command.getName());

        return categoryRepository.save(category);
    }
}
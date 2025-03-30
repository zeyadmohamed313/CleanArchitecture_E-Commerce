package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Category.Command.Handler;

import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Category.Command.Models.DeleteCategoryCommand;
import com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteCategoryHandler {

    private final CategoryRepository categoryRepository;

    public DeleteCategoryHandler(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void handle(DeleteCategoryCommand command) {
        if (!categoryRepository.existsById(command.getId())) {
            throw new IllegalArgumentException("Category not found");
        }

        categoryRepository.deleteById(command.getId());
    }
}

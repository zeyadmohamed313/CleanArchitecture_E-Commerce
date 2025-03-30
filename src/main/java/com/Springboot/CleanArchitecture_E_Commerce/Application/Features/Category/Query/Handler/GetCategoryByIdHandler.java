package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Category.Query.Handler;

import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Category.Query.Models.GetCategoryByIdQuery;
import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.Category;
import com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetCategoryByIdHandler {

    private final CategoryRepository categoryRepository;

    public GetCategoryByIdHandler(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> handle(GetCategoryByIdQuery query) {
        return categoryRepository.findById(query.getId());
    }
}
package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Category.Query.Handler;

import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Category.Query.Models.GetAllCategoriesQuery;
import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.Category;
import com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllCategoriesHandler {

    private final CategoryRepository categoryRepository;

    public GetAllCategoriesHandler(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> handle(GetAllCategoriesQuery query) {
        return categoryRepository.findAll();
    }
}
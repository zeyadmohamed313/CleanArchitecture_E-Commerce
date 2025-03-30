package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Product.Command.Handlers;

import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Product.Command.Models.CreateProductCommand;
import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.Category;
import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.Product;
import com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories.CategoryRepository;
import com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateProductHandler {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public CreateProductHandler(ProductRepository productRepository , CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public String handle(CreateProductCommand command) {
        try {
            Category category = categoryRepository.findById(command.getCategory_id())  // ✅ Use 1L for Long ID
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            Product product = new Product(command.getName(),
                    command.getDescription(), command.getPrice(),
                    command.getStock(), category, command.getImageUrl());

            productRepository.save(product);
            return "Success";  // ✅ Return Success if saved
        } catch (Exception e) {
            return "Failed: " + e.getMessage();  // ❌ Return Failed with error message
        }
    }
}
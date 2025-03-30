package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Category.Command.Handler;

import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.Category;
import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.Product;

import com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories.CategoryRepository;
import com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AddProductToCategoryHandler {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public AddProductToCategoryHandler(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public boolean handle(Long categoryId, Long productId) {
        Optional<Category> categoryOpt = categoryRepository.findById(categoryId);
        Optional<Product> productOpt = productRepository.findById(productId);

        if (categoryOpt.isPresent() && productOpt.isPresent()) {
            Category category = categoryOpt.get();
            Product product = productOpt.get();
            product.setCategory(category);
            productRepository.save(product);
            return true;
        }
        return false;
    }
}

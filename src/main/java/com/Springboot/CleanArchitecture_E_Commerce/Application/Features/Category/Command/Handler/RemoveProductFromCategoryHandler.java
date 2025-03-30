package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Category.Command.Handler;

import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.Product;
import com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class RemoveProductFromCategoryHandler {

    private final ProductRepository productRepository;

    public RemoveProductFromCategoryHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean handle(Long categoryId, Long productId) {
        Optional<Product> productOpt = productRepository.findById(productId);

        if (productOpt.isPresent() && productOpt.get().getCategory().getId().equals(categoryId)) {
            Product product = productOpt.get();
            product.setCategory(null);
            productRepository.save(product);
            return true;
        }
        return false;
    }
}
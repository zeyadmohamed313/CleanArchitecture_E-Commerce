package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Product.Command.Handlers;

import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Product.Command.Models.UpdateProductCommand;
import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.Product;
import com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductHandler {

    private final ProductRepository productRepository;

    public UpdateProductHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product handle(UpdateProductCommand command) {
        return productRepository.findById(command.getId())
                .map(existingProduct -> {
                    existingProduct.setName(command.getName());
                    existingProduct.setDescription(command.getDescription());
                    existingProduct.setPrice(command.getPrice());
                    existingProduct.setStock(command.getStock());
                    existingProduct.setImgUrl(command.getImageUrl());
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}

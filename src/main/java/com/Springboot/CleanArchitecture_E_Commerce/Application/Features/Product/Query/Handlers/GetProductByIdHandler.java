package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Product.Query.Handlers;

import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Product.Query.Model.GetProductByIdQuery;
import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.Product;
import com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetProductByIdHandler {

    private final ProductRepository productRepository;

    public GetProductByIdHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> handle(GetProductByIdQuery query) {
        return productRepository.findById(query.getId());
    }
}
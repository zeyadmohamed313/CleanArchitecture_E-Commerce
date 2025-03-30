package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Product.Query.Handlers;

import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Product.Query.Model.GetAllProductsQuery;
import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.Product;
import com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsHandler {

    private final ProductRepository productRepository;

    public GetAllProductsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> handle(GetAllProductsQuery query) {
        List<Product> products = productRepository.findAll();
        return products;
    }
}
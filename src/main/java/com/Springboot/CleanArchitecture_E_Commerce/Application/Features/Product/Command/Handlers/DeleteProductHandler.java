package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Product.Command.Handlers;


import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Product.Command.Models.DeleteProductCommand;
import com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductHandler {

    private final ProductRepository productRepository;

    public DeleteProductHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void handle(DeleteProductCommand command) {
        productRepository.deleteById(command.getId());
    }
}

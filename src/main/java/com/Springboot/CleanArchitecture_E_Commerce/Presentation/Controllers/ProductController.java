package com.Springboot.CleanArchitecture_E_Commerce.Presentation.Controllers;

import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Product.Command.Handlers.CreateProductHandler;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Product.Command.Handlers.DeleteProductHandler;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Product.Command.Handlers.UpdateProductHandler;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Product.Command.Models.CreateProductCommand;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Product.Command.Models.DeleteProductCommand;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Product.Command.Models.UpdateProductCommand;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Product.Query.Handlers.GetAllProductsHandler;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Product.Query.Handlers.GetProductByIdHandler;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Product.Query.Model.GetAllProductsQuery;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Product.Query.Model.GetProductByIdQuery;
import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.Product;
import com.Springboot.CleanArchitecture_E_Commerce.Presentation.ResponseSchema.ApiResponse;
import com.Springboot.CleanArchitecture_E_Commerce.Presentation.Router.ApiRoutes;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiRoutes.PRODUCT_BASE) // Uses route from ApiRoutes
public class ProductController {

    private final CreateProductHandler createHandler;
    private final UpdateProductHandler updateHandler;
    private final DeleteProductHandler deleteHandler;
    private final GetProductByIdHandler getByIdHandler;
    private final GetAllProductsHandler getAllHandler;

    public ProductController(CreateProductHandler createHandler, UpdateProductHandler updateHandler,
                             DeleteProductHandler deleteHandler, GetProductByIdHandler getByIdHandler,
                             GetAllProductsHandler getAllHandler) {
        this.createHandler = createHandler;
        this.updateHandler = updateHandler;
        this.deleteHandler = deleteHandler;
        this.getByIdHandler = getByIdHandler;
        this.getAllHandler = getAllHandler;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<String>> createProduct(@Valid @RequestBody CreateProductCommand command) {
        String createdProduct = createHandler.handle(command);
        if ("Success".equals(createdProduct)) {
            return ResponseEntity.ok(ApiResponse.success("Product created successfully", createdProduct));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Product creation failed"));
        }
    }

    @PutMapping(ApiRoutes.PRODUCT_BY_ID) // Uses route from ApiRoutes
    public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable Long id,@Valid @RequestBody UpdateProductCommand command) {
        Product updatedProduct = updateHandler.handle(new UpdateProductCommand(
                id, command.getName(), command.getDescription(), command.getPrice(), command.getStock(), command.getImageUrl()));
        if (updatedProduct != null) {
            return ResponseEntity.ok(ApiResponse.success("Product updated successfully", updatedProduct));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Product not found"));
        }
    }

    @GetMapping(ApiRoutes.PRODUCT_BY_ID) // Uses route from ApiRoutes
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Long id) {
        Optional<Product> product = getByIdHandler.handle(new GetProductByIdQuery(id));
        return product.map(value -> ResponseEntity.ok(ApiResponse.success("Product found", value)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("Product not found")));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        List<Product> products = getAllHandler.handle(new GetAllProductsQuery());
        return ResponseEntity.ok(ApiResponse.success("Products retrieved successfully", products));
    }

    @DeleteMapping(ApiRoutes.PRODUCT_BY_ID) // Uses route from ApiRoutes
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id) {
        try {
            deleteHandler.handle(new DeleteProductCommand(id));
            return ResponseEntity.ok(ApiResponse.success("Product deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to delete product"));
        }
    }
}

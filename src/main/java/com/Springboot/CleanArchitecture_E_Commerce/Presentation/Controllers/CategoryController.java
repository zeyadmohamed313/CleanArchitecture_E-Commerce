package com.Springboot.CleanArchitecture_E_Commerce.Presentation.Controllers;


import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Category.Command.Handler.*;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Category.Command.Models.*;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Category.Query.Handler.GetAllCategoriesHandler;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Category.Query.Handler.GetCategoryByIdHandler;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Category.Query.Models.GetAllCategoriesQuery;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Category.Query.Models.GetCategoryByIdQuery;
import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.Category;
import com.Springboot.CleanArchitecture_E_Commerce.Presentation.ResponseSchema.ApiResponse;
import com.Springboot.CleanArchitecture_E_Commerce.Presentation.Router.ApiRoutes;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiRoutes.BASE_API + "/categories") // Base route: /api/categories
public class CategoryController {

    private final CreateCategoryHandler createHandler;
    private final UpdateCategoryHandler updateHandler;
    private final DeleteCategoryHandler deleteHandler;
    private final GetCategoryByIdHandler getByIdHandler;
    private final GetAllCategoriesHandler getAllHandler;
    private final AddProductToCategoryHandler addProductHandler;
    private final RemoveProductFromCategoryHandler removeProductHandler;

    public CategoryController(CreateCategoryHandler createHandler, UpdateCategoryHandler updateHandler,
                              DeleteCategoryHandler deleteHandler, GetCategoryByIdHandler getByIdHandler,
                              GetAllCategoriesHandler getAllHandler,AddProductToCategoryHandler addProductHandler
    , RemoveProductFromCategoryHandler removeProductHandler) {
        this.createHandler = createHandler;
        this.updateHandler = updateHandler;
        this.deleteHandler = deleteHandler;
        this.getByIdHandler = getByIdHandler;
        this.getAllHandler = getAllHandler;
        this.addProductHandler = addProductHandler;
        this.removeProductHandler = removeProductHandler;
    }

    // ✅ Get all categories
    @GetMapping
    public ResponseEntity<ApiResponse<List<Category>>> getAllCategories() {
        List<Category> categories = getAllHandler.handle(new GetAllCategoriesQuery());
        return ResponseEntity.ok(ApiResponse.success("Categories retrieved successfully", categories));
    }

    // ✅ Get category by ID
    @GetMapping(ApiRoutes.CATEGORY_BY_ID) // /api/categories/{id}
    public ResponseEntity<ApiResponse<Category>> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = getByIdHandler.handle(new GetCategoryByIdQuery(id));
        return category.map(value -> ResponseEntity.ok(ApiResponse.success("Category found", value)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("Category not found")));
    }

    // ✅ Create a new category
    @PostMapping
    public ResponseEntity<ApiResponse<Category>> createCategory(@Valid @RequestBody CreateCategoryCommand command) {
        Category createdCategory = createHandler.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Category created successfully", createdCategory));
    }

    // ✅ Update an existing category
    @PutMapping(ApiRoutes.CATEGORY_BY_ID) // /api/categories/{id}
    public ResponseEntity<ApiResponse<Category>> updateCategory(@PathVariable Long id, @Valid @RequestBody UpdateCategoryCommand command) {
        command.setId(id);
        Category updatedCategory = updateHandler.handle(command);
        return ResponseEntity.ok(ApiResponse.success("Category updated successfully", updatedCategory));
    }

    // ✅ Delete a category
    @DeleteMapping(ApiRoutes.CATEGORY_BY_ID) // /api/categories/{id}
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Long id) {
        try {
            deleteHandler.handle(new DeleteCategoryCommand(id));
            return ResponseEntity.ok(ApiResponse.success("Category deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to delete category"));
        }


    }


    @PostMapping(ApiRoutes.CATEGORY_ADD_PRODUCT)
    public ResponseEntity<ApiResponse<String>> addProductToCategory(
            @Valid @RequestBody AddProductToCategoryRequest request) {
        boolean added = addProductHandler.handle(request.getCategoryId(), request.getProductId());
        if (added) {
            return ResponseEntity.ok(ApiResponse.success("Product added to category successfully", null));
        } else {
            return ResponseEntity.badRequest().body(ApiResponse.error("Failed to add product to category"));
        }
    }

    @DeleteMapping(ApiRoutes.CATEGORY_ADD_PRODUCT)
    public ResponseEntity<ApiResponse<String>> removeProductFromCategory(
            @Valid @RequestBody RemoveProductFromCategoryRequest request) {
        boolean removed = removeProductHandler.handle(request.getCategoryId(), request.getProductId());
        if (removed) {
            return ResponseEntity.ok(ApiResponse.success("Product removed from category successfully", null));
        } else {
            return ResponseEntity.badRequest().body(ApiResponse.error("Failed to remove product from category"));
        }
    }
}
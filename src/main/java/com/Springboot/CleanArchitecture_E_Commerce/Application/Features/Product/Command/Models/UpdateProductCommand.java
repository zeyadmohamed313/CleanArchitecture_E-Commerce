package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Product.Command.Models;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateProductCommand {

    @NotNull(message = "Product ID is required")
    private Long id;
    @NotNull(message = "Category ID is required")
    private Long category_id;

    public UpdateProductCommand(Long id, String name, String description, BigDecimal price, int stock, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Long getCategory_id() {
        return category_id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NotBlank(message = "Product name is required")
    private String name;
    @NotBlank(message = "Description cannot be empty")
    private String description;
    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be at least 1")
    private BigDecimal price;
    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock cannot be negative")
    private int stock;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    private String imageUrl;
}
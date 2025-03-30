package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Cart.Query.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemResponse {

    private Long cartItemId;
    private Long productId;
    private String productName;
    private int quantity;
    public CartItemResponse() {}

    public Long getCartItemId() {
        return cartItemId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public CartItemResponse(Long cartItemId, Long productId, String productName, int quantity) {
        this.cartItemId = cartItemId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
    }
}
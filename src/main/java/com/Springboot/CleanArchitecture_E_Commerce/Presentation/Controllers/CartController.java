package com.Springboot.CleanArchitecture_E_Commerce.Presentation.Controllers;

import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Cart.Command.Handler.AddToCartHandler;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Cart.Command.Handler.RemoveFromCartHandler;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Cart.Command.Model.AddToCartCommand;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Cart.Command.Model.RemoveFromCartCommand;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Cart.Query.Handler.GetCartHandler;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Cart.Query.Model.CartItemResponse;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Cart.Query.Model.GetCartQuery;
import com.Springboot.CleanArchitecture_E_Commerce.Presentation.ResponseSchema.ApiResponse;
import com.Springboot.CleanArchitecture_E_Commerce.Presentation.Router.ApiRoutes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.CART_BASE)
public class CartController {

    private final AddToCartHandler addToCartHandler;
    private final RemoveFromCartHandler removeFromCartHandler;
    private final GetCartHandler getCartHandler;

    public CartController(AddToCartHandler addToCartHandler, RemoveFromCartHandler removeFromCartHandler, GetCartHandler getCartHandler) {
        this.addToCartHandler = addToCartHandler;
        this.removeFromCartHandler = removeFromCartHandler;
        this.getCartHandler = getCartHandler;
    }

    @PostMapping(ApiRoutes.CART_ADD)
    public ResponseEntity<ApiResponse<String>> addToCart(@RequestBody AddToCartCommand command) {
        String message = addToCartHandler.handle(command);
        return ResponseEntity.ok(ApiResponse.success(message, null));
    }

    @DeleteMapping(ApiRoutes.CART_REMOVE)
    public ResponseEntity<ApiResponse<String>> removeFromCart(@RequestBody RemoveFromCartCommand command) {
        String message = removeFromCartHandler.handle(command);
        return ResponseEntity.ok(ApiResponse.success(message, null));
    }

    @GetMapping(ApiRoutes.CART_GET)
    public ResponseEntity<ApiResponse<List<CartItemResponse>>> getCart(@PathVariable Long userId) {
        List<CartItemResponse> cartItems = getCartHandler.handle(new GetCartQuery(userId));
        return ResponseEntity.ok(ApiResponse.success("Cart retrieved successfully", cartItems));
    }
}
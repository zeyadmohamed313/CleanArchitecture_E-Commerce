package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Cart.Command.Handler;

import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Cart.Command.Model.AddToCartCommand;
import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.Cart;
import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.CartItem;
import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.Product;
import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.User;
import com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories.CartRepository;
import com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories.ProductRepository;
import com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AddToCartHandler {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public AddToCartHandler(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public String handle(AddToCartCommand command) {
        User user = userRepository.findById(command.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(command.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = cartRepository.findByUser(user).orElse(new Cart(user));
        CartItem cartItem = new CartItem(cart, product, command.getQuantity());
        cart.getCartItems().add(cartItem);

        cartRepository.save(cart);
        return "Product added to cart successfully!";
    }
}
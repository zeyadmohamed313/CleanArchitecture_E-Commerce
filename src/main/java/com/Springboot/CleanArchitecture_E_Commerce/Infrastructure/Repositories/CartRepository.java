package com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories;

import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.Cart;
import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.CartItem;
import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.CartItem;
import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
    Optional<Cart> findByUser_Id(Long userId); // Correct method name

}

package com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories;

import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}

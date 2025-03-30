package com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories;

import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
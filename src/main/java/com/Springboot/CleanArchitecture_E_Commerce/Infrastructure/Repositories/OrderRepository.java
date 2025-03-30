package com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories;


import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.Order;
import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
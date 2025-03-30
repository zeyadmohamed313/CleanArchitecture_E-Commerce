package com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories;

import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
}
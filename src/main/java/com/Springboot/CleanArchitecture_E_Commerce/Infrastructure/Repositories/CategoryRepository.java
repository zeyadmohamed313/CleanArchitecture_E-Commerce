package com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories;

import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Custom query methods can be added here if needed
    Category findByName(String name);
}
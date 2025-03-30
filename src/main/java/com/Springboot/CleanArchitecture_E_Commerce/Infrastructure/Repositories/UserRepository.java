package com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories;

import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
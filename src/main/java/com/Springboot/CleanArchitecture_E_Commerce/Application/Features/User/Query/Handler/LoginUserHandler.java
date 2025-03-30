package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.User.Query.Handler;

import com.Springboot.CleanArchitecture_E_Commerce.Application.Config.JwtUtil;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.User.Query.Model.LoginRequest;
import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.User;
import com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginUserHandler {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginUserHandler(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.jwtUtil = jwtUtil;
    }

    public Optional<String> handle(LoginRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent() && passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
            return Optional.of(jwtUtil.generateToken(user.get().getEmail()));
        }
        return Optional.empty();
    }
}

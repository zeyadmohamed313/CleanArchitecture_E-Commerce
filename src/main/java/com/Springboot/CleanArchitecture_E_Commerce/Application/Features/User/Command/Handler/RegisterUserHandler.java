package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.User.Handlers;


import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.User.Command.Model.RegisterUserRequest;
import com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites.User;
import com.Springboot.CleanArchitecture_E_Commerce.Domain.Helper.Role;
import com.Springboot.CleanArchitecture_E_Commerce.Infrastructure.Repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserHandler {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public RegisterUserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String handle(RegisterUserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already exists";
        }

        User user = new User(
                request.getUsername(),  // Now passing username correctly
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                Role.USER
        );

        userRepository.save(user);
        return "User registered successfully";
    }
}

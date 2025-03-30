package com.Springboot.CleanArchitecture_E_Commerce.Presentation.Controllers;

import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.User.Command.Model.RegisterUserRequest;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.User.Query.Handler.LoginUserHandler;
import com.Springboot.CleanArchitecture_E_Commerce.Application.Features.User.Query.Model.LoginRequest;
import com.Springboot.CleanArchitecture_E_Commerce.Presentation.ResponseSchema.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final com.Springboot.CleanArchitecture_E_Commerce.Application.Features.User.Handlers.RegisterUserHandler registerUserHandler;
    private final LoginUserHandler loginUserHandler;

    public UserController(com.Springboot.CleanArchitecture_E_Commerce.Application.Features.User.Handlers.RegisterUserHandler registerUserHandler, LoginUserHandler loginUserHandler) {
        this.registerUserHandler = registerUserHandler;
        this.loginUserHandler = loginUserHandler;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@Valid @RequestBody RegisterUserRequest command) {
        String response = registerUserHandler.handle(command);
        return ResponseEntity.ok(ApiResponse.success("User registered successfully", response));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@Valid @RequestBody LoginRequest command) {
       String token = loginUserHandler.handle(command).orElseThrow();
        return ResponseEntity.ok(ApiResponse.success("Login successful", token));
    }
}

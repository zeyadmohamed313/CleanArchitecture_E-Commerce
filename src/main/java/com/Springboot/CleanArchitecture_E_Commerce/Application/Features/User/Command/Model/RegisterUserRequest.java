package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.User.Command.Model;

import com.Springboot.CleanArchitecture_E_Commerce.Domain.Helper.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequest {
    @NotBlank(message = "Username is required")
    private String username;

    public void setUsername(@NotBlank(message = "Username is required") String username) {
        this.username = username;
    }

    public void setEmail(@NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email) {
        this.email = email;
    }

    public void setPassword(@NotBlank(message = "Password is required") String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    public @NotBlank(message = "Username is required") String getUsername() {
        return username;
    }

    public @NotBlank(message = "Email is required") @Email(message = "Invalid email format") String getEmail() {
        return email;
    }

    public @NotBlank(message = "Password is required") String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    @NotBlank(message = "Password is required")
    private String password;

    private Role role = Role.USER; // Default role
}
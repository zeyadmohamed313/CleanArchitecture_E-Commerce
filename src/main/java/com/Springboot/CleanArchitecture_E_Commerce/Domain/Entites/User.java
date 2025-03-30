package com.Springboot.CleanArchitecture_E_Commerce.Domain.Entites;

import com.Springboot.CleanArchitecture_E_Commerce.Domain.Helper.Role;
import io.swagger.v3.oas.annotations.info.Contact;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    private String email;

    public User(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(@NotBlank(message = "Username is required") String username) {
        this.username = username;
    }

    public void setEmail(@NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPassword(@NotBlank(message = "Password is required") String password) {
        this.password = password;
    }

    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    private String password;

    public Role getRole() {
        return role;
    }

    public @NotBlank(message = "Username is required") String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public @NotBlank(message = "Email is required") @Email(message = "Invalid email format") String getEmail() {
        return email;
    }

    public @NotBlank(message = "Password is required") String getPassword() {
        return password;
    }

    public User(){}
//    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}


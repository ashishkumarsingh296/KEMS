package com.kems.Kems.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;

    // If Lombok doesn't work, manually add getters
    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}

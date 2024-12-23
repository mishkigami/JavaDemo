package com.example.demo.dto.admin;

import lombok.Data;
import java.time.LocalDateTime;

import com.example.demo.security.model.Role;

@Data
public class UserAdminResponseDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 
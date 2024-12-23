package com.example.demo.controller.admin;

import com.example.demo.dto.admin.UserAdminCreateDTO;
import com.example.demo.dto.admin.UserAdminResponseDTO;
import com.example.demo.dto.admin.UserAdminUpdateDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserAdminResponseDTO> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(userMapper::toAdminDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserAdminResponseDTO getUser(@PathVariable Long id) {
        return userMapper.toAdminDto(userService.getUserById(id));
    }

    @PostMapping
    public UserAdminResponseDTO createUser(@Valid @RequestBody UserAdminCreateDTO createDTO) {
        User user = userMapper.toEntity(createDTO);
        return userMapper.toAdminDto(userService.createUser(user));
    }

    @PutMapping("/{id}")
    public UserAdminResponseDTO updateUser(@PathVariable Long id, 
                                         @Valid @RequestBody UserAdminUpdateDTO updateDTO) {
        return userMapper.toAdminDto(userService.updateUser(id, updateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
} 
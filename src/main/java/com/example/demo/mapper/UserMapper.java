package com.example.demo.mapper;

import com.example.demo.dto.admin.UserAdminCreateDTO;
import com.example.demo.dto.admin.UserAdminResponseDTO;
import com.example.demo.dto.admin.UserAdminUpdateDTO;
import com.example.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    // Конвертация из Entity в DTO для ответа
    UserAdminResponseDTO toAdminDto(User user);
    
    // Конвертация из DTO создания в Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toEntity(UserAdminCreateDTO dto);
    
    // Обновление существующего Entity из DTO обновления
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateUserFromDto(UserAdminUpdateDTO dto, @MappingTarget User user);
    
    // Конвертация списка Entity в список DTO
    List<UserAdminResponseDTO> toDtoList(List<User> users);
} 
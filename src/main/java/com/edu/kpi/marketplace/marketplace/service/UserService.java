package com.edu.kpi.marketplace.marketplace.service;

import com.edu.kpi.marketplace.marketplace.dto.UserDto;
import com.edu.kpi.marketplace.marketplace.model.User;
import com.edu.kpi.marketplace.marketplace.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto create(UserDto userDto) {

        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new IllegalStateException("User already exists");
        }

        User user = toEntity(userDto);
        user.setRole("USER");

        return toDto(userRepository.save(user));

    }

    public UserDto findById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        return toDto(user);
    }

    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void updateUser(String id, UserDto userDTO) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        existingUser.setEmail(userDTO.getEmail());

        if (userDTO.getPassword() != null && !userDTO.getPassword().isBlank()) {
            //existingUser.setPasswordHash(passwordEncoder.encode(userDTO.getPassword()));
        }
        userRepository.save(existingUser);
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }

    public String getUserIdByEmail(String email) {
        return userRepository.findByEmail(email).getId();
    }

    private User toEntity(UserDto dto) {
        if (dto == null) return null;

        return User.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .passwordHash(dto.getPassword())
                .role(dto.getRole() != null ? dto.getRole() : "USER")
                .build();
    }

    private UserDto toDto(User entity) {
        if (entity == null) return null;

        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setRole(entity.getRole());
        return dto;
    }
}

package com.edu.kpi.marketplace.marketplace.controller;

import com.edu.kpi.marketplace.marketplace.dto.UserDto;
import com.edu.kpi.marketplace.marketplace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public List<UserDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto userDto) {
        return service.create(userDto);
    }

    @PutMapping("/{id}")
    public void update(
            @PathVariable String id,
            @RequestBody UserDto userDto
    ) {
        service.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}

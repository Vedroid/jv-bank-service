package ua.vedroid.bankservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.vedroid.bankservice.entity.User;
import ua.vedroid.bankservice.entity.dto.UserRequestDto;
import ua.vedroid.bankservice.entity.dto.UserResponseDto;
import ua.vedroid.bankservice.mapper.UserMapper;
import ua.vedroid.bankservice.service.UserService;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    @PostMapping
    public UserResponseDto create(@RequestBody UserRequestDto dto) {
        return mapper.toDto(service.create(mapper.toEntity(dto)));
    }

    @PutMapping("/{userId}")
    public UserResponseDto update(@RequestBody UserRequestDto dto, @PathVariable Long userId) {
        User user = mapper.toEntity(dto);
        user.setId(userId);
        return mapper.toDto(service.update(user));
    }

    @GetMapping("/{userId}")
    public UserResponseDto getById(@PathVariable Long userId) {
        return mapper.toDto(service.findById(userId));
    }

    @GetMapping("/by-phone")
    public UserResponseDto getByPhoneNumber(@RequestParam String phoneNumber) {
        return mapper.toDto(service.findByPhoneNumber(phoneNumber));
    }

    @DeleteMapping("/{userId}")
    public String delete(@PathVariable Long userId) {
        service.delete(userId);
        return "User by id " + userId + " deleted";
    }
}

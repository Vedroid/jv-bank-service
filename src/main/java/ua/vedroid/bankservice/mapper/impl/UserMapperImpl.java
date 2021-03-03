package ua.vedroid.bankservice.mapper.impl;

import java.time.LocalDate;
import org.springframework.stereotype.Component;
import ua.vedroid.bankservice.entity.User;
import ua.vedroid.bankservice.entity.dto.UserRequestDto;
import ua.vedroid.bankservice.entity.dto.UserResponseDto;
import ua.vedroid.bankservice.mapper.UserMapper;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User toEntity(UserRequestDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
        return user;
    }

    @Override
    public UserResponseDto toDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .dateOfBirth(user.getDateOfBirth().toString())
                .build();
    }
}

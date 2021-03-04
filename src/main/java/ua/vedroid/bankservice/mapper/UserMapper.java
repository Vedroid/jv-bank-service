package ua.vedroid.bankservice.mapper;

import ua.vedroid.bankservice.entity.User;
import ua.vedroid.bankservice.entity.dto.UserRequestDto;
import ua.vedroid.bankservice.entity.dto.UserResponseDto;

public interface UserMapper {
    User toEntity(UserRequestDto dto);

    UserResponseDto toDto(User user);
}

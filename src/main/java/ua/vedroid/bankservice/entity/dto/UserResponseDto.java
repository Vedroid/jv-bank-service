package ua.vedroid.bankservice.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    private Long id;
    private String name;
    private String dateOfBirth;
    private String phoneNumber;
}

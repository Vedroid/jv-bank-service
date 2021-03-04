package ua.vedroid.bankservice.entity.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String name;
    private String dateOfBirth;
    private String phoneNumber;
    private String password;
}

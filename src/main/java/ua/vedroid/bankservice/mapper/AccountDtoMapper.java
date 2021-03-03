package ua.vedroid.bankservice.mapper;

import ua.vedroid.bankservice.entity.Account;
import ua.vedroid.bankservice.entity.dto.AccountRequestDto;
import ua.vedroid.bankservice.entity.dto.AccountResponseDto;

public interface AccountDtoMapper {
    Account toEntity(AccountRequestDto dto);

    AccountResponseDto toDto(Account account);
}

package ua.vedroid.bankservice.mapper.impl;

import java.util.Currency;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ua.vedroid.bankservice.entity.Account;
import ua.vedroid.bankservice.entity.dto.AccountRequestDto;
import ua.vedroid.bankservice.entity.dto.AccountResponseDto;
import ua.vedroid.bankservice.mapper.AccountDtoMapper;
import ua.vedroid.bankservice.service.UserService;

@Component
@AllArgsConstructor
public class AccountDtoMapperImpl implements AccountDtoMapper {
    private final UserService service;

    @Override
    public Account toEntity(AccountRequestDto dto) {
        Account account = new Account();
        account.setAccountNumber(dto.getAccountNumber());
        account.setUser(service.findById(dto.getUserId()));
        account.setBalance(dto.getBalance());
        account.setCurrency(Currency.getInstance(dto.getCurrency()));
        account.setActive(true);
        return account;
    }

    @Override
    public AccountResponseDto toDto(Account account) {
        return AccountResponseDto.builder()
                .accountNumber(account.getAccountNumber())
                .currency(account.getCurrency().toString())
                .balance(account.getBalance())
                .build();
    }
}

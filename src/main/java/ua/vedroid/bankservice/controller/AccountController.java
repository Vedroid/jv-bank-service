package ua.vedroid.bankservice.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.vedroid.bankservice.entity.Account;
import ua.vedroid.bankservice.entity.dto.AccountRequestDto;
import ua.vedroid.bankservice.entity.dto.AccountResponseDto;
import ua.vedroid.bankservice.entity.dto.TransactionRequestDto;
import ua.vedroid.bankservice.entity.dto.TransactionResponseDto;
import ua.vedroid.bankservice.mapper.AccountDtoMapper;
import ua.vedroid.bankservice.mapper.TransactionDtoMapper;
import ua.vedroid.bankservice.service.AccountService;
import ua.vedroid.bankservice.service.TransactionService;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {
    private final TransactionService transactionService;
    private final AccountService accountService;
    private final TransactionDtoMapper transactionMapper;
    private final AccountDtoMapper accountMapper;

    @PostMapping
    public AccountResponseDto create(@RequestBody AccountRequestDto dto) {
        Account account = accountMapper.toEntity(dto);
        account.setActive(true);
        return accountMapper.toDto(accountService.save(account));
    }

    @GetMapping("/by-phone")
    public List<AccountResponseDto> getAllByPhoneNumber(@RequestParam String phoneNumber) {
        return accountService.getAllByUserPhoneNumber(phoneNumber).stream()
                .map(accountMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/transfer")
    public String transfer(@RequestBody TransactionRequestDto dto) {
        transactionService.transfer(dto);
        return "Transfer completed";
    }

    @GetMapping("/{accountNumber}")
    public BigDecimal getBalance(@PathVariable String accountNumber) {
        return accountService.findByAccountNumber(accountNumber).getBalance();
    }

    @GetMapping("/history/{accountNumber}")
    public List<TransactionResponseDto> getHistory(@PathVariable String accountNumber,
                                                   @RequestParam(defaultValue = "1")
                                                           Integer page,
                                                   @RequestParam(defaultValue = "10")
                                                           Integer limit) {
        return transactionService.getHistory(accountNumber, page, limit).stream()
                .map(transactionMapper::toDto)
                .collect(Collectors.toList());
    }

    @PatchMapping("/{accountNumber}")
    public void block(@PathVariable String accountNumber) {
        Account account = accountService.findByAccountNumber(accountNumber);
        account.setActive(false);
        accountService.save(account);
    }
}

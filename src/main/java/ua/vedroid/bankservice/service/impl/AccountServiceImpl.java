package ua.vedroid.bankservice.service.impl;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.vedroid.bankservice.entity.Account;
import ua.vedroid.bankservice.exception.NoEntityException;
import ua.vedroid.bankservice.repository.AccountRepository;
import ua.vedroid.bankservice.service.AccountService;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).orElseThrow(() ->
                new NoEntityException("Account not found by accountNumber: " + accountNumber));
    }

    @Override
    public List<Account> getAllByUserPhoneNumber(String phoneNumber) {
        return accountRepository.findAllByUserPhoneNumber(phoneNumber);
    }
}

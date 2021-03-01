package ua.vedroid.bankservice.service;

import java.util.List;
import ua.vedroid.bankservice.entity.Account;

public interface AccountService {
    Account create(Account account);

    Account findByAccountNumber(String accountNumber);

    List<Account> getAllByUserPhoneNumber(String phoneNumber);

    void blockByAccountNumber(String accountNumber);
}

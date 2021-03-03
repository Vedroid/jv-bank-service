package ua.vedroid.bankservice.service;

import java.util.List;
import ua.vedroid.bankservice.entity.Transaction;
import ua.vedroid.bankservice.entity.dto.TransactionRequestDto;

public interface TransactionService {
    void transfer(TransactionRequestDto transactionRequestDto);

    List<Transaction> getHistory(String accountNumber, int page, int size);
}

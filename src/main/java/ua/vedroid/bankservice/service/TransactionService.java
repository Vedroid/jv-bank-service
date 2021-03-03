package ua.vedroid.bankservice.service;

import java.util.List;
import ua.vedroid.bankservice.entity.Transaction;
import ua.vedroid.bankservice.entity.dto.TransactionDto;

public interface TransactionService {
    void transfer(TransactionDto transactionDto);

    List<Transaction> getHistory(String accountNumber, int page, int size);
}

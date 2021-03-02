package ua.vedroid.bankservice.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.vedroid.bankservice.entity.Account;
import ua.vedroid.bankservice.entity.Transaction;
import ua.vedroid.bankservice.entity.dto.TransactionDto;
import ua.vedroid.bankservice.exception.InsufficientFundsException;
import ua.vedroid.bankservice.repository.TransactionRepository;
import ua.vedroid.bankservice.service.AccountService;
import ua.vedroid.bankservice.service.TransactionService;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    @Override
    @Transactional
    public void transfer(TransactionDto dto) {
        Account fromAccount = accountService.findByAccountNumber(dto.getFromAccount());
        Account toAccount = accountService.findByAccountNumber(dto.getToAccount());
        BigDecimal amount = dto.getAmount();
        BigDecimal fromAccountBalance = fromAccount.getBalance();
        BigDecimal currentAmount = fromAccountBalance.subtract(amount);
        if (currentAmount.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new InsufficientFundsException("Insufficient funds on account " + fromAccount);
        }
        fromAccount.setBalance(currentAmount);
        toAccount.setBalance(toAccount.getBalance().add(amount));
        LocalDateTime now = LocalDateTime.now();
        Transaction outcomingTransaction = Transaction.builder()
                .fromAccount(fromAccount)
                .toAccount(toAccount)
                .amount(amount)
                .date(now)
                .type(Transaction.TransactionType.OUTCOMING)
                .build();
        Transaction incomingTransaction = Transaction.builder()
                .fromAccount(fromAccount)
                .toAccount(toAccount)
                .amount(amount)
                .date(now)
                .type(Transaction.TransactionType.INCOMING)
                .build();
        transactionRepository.save(outcomingTransaction);
        transactionRepository.save(incomingTransaction);
    }

    @Override
    public List<Transaction> getHistory(String accountNumber, int page, int limit) {
        PageRequest pageRequest = PageRequest
                .of(page - 1, limit, Sort.by("date").descending().and(Sort.by("id")));
        return transactionRepository.findAllByAccountNumber(accountNumber, pageRequest);
    }
}

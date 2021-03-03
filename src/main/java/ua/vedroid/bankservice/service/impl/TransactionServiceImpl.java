package ua.vedroid.bankservice.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
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
import ua.vedroid.bankservice.util.ExchangeRateRequester;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final ExchangeRateRequester rateRequester;

    @Override
    @Transactional
    public void transfer(TransactionDto dto) {
        Account fromAccount = accountService.findByAccountNumber(dto.getFromAccount());
        Account toAccount = accountService.findByAccountNumber(dto.getToAccount());
        BigDecimal amount = dto.getAmount();
        BigDecimal fromAccountBalance = fromAccount.getBalance();
        BigDecimal currentAmount = fromAccountBalance.subtract(amount);
        if (currentAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientFundsException("Insufficient funds on account " + fromAccount);
        }
        double rate = rateRequester.getRate(fromAccount.getCurrency(),
                toAccount.getCurrency(), LocalDate.now());
        BigDecimal convertedAmount = amount.multiply(BigDecimal.valueOf(rate));
        fromAccount.setBalance(currentAmount);
        toAccount.setBalance(toAccount.getBalance().add(convertedAmount));
        accountService.save(fromAccount);
        accountService.save(toAccount);
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
                .amount(convertedAmount)
                .date(now)
                .type(Transaction.TransactionType.INCOMING)
                .build();
        transactionRepository.save(outcomingTransaction);
        transactionRepository.save(incomingTransaction);
    }

    @Override
    public List<Transaction> getHistory(String accountNumber, int page, int limit) {
        PageRequest pageRequest = PageRequest
                .of(page, limit, Sort.by("date").descending().and(Sort.by("id")));
        return transactionRepository.findAllByAccountNumber(accountNumber, pageRequest);
    }
}

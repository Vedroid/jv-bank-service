package ua.vedroid.bankservice.mapper.impl;

import org.springframework.stereotype.Component;
import ua.vedroid.bankservice.entity.Transaction;
import ua.vedroid.bankservice.entity.dto.TransactionResponseDto;
import ua.vedroid.bankservice.mapper.TransactionDtoMapper;

@Component
public class TransactionDtoMapperImpl implements TransactionDtoMapper {
    @Override
    public TransactionResponseDto toDto(Transaction transaction) {
        return TransactionResponseDto.builder()
                .id(transaction.getId())
                .fromAccount(transaction.getFromAccount().getAccountNumber())
                .toAccount(transaction.getToAccount().getAccountNumber())
                .amount(transaction.getAmount())
                .date(transaction.getDate().toString())
                .type(transaction.getType().toString())
                .build();
    }
}

package ua.vedroid.bankservice.mapper;

import ua.vedroid.bankservice.entity.Transaction;
import ua.vedroid.bankservice.entity.dto.TransactionResponseDto;

public interface TransactionDtoMapper {
    TransactionResponseDto toDto(Transaction transaction);
}

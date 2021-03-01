package ua.vedroid.bankservice.entity.dto;

import lombok.Data;

@Data
public class TransactionDto {
    private String fromAccount;
    private String toAccount;
    private double amount;
}

package ua.vedroid.bankservice.entity.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransactionDto {
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
}

package ua.vedroid.bankservice.entity.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponseDto {
    private Long id;
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
    private String date;
    private String type;
}

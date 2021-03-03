package ua.vedroid.bankservice.entity.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponseDto {
    private Long id;
    private String accountNumber;
    private String currency;
    private BigDecimal balance;
    private boolean isActive;
}

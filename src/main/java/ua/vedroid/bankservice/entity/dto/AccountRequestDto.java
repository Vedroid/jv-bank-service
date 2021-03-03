package ua.vedroid.bankservice.entity.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class AccountRequestDto {
    private String accountNumber;
    private Long userId;
    private String currency;
    private BigDecimal balance;
}

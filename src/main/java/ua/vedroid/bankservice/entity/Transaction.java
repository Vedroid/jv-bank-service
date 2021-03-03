package ua.vedroid.bankservice.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transactions")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "account_from", nullable = false)
    private Account fromAccount;
    @ManyToOne
    @JoinColumn(name = "account_to", nullable = false)
    private Account toAccount;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private TransactionType type;

    public enum TransactionType {
        INCOMING, OUTCOMING
    }
}

package ua.vedroid.bankservice.entity;

import java.time.LocalDateTime;
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
    @JoinColumn(name = "account_from")
    private Account fromAccount;
    @ManyToOne
    @JoinColumn(name = "account_to")
    private Account toAccount;
    private LocalDateTime date;
    private Double amount;
    private TransactionType type;

    public enum TransactionType {
        INCOMING, OUTCOMING
    }
}

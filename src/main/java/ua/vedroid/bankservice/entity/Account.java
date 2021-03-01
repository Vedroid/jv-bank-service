package ua.vedroid.bankservice.entity;

import java.util.Currency;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "accounts")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_number", unique = true)
    private String accountNumber;
    private Currency currency;
    private Double balance;
    @Column(name = "is_active")
    private boolean isActive;
    @ManyToOne
    @ToString.Exclude
    private User user;
}

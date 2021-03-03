package ua.vedroid.bankservice.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.vedroid.bankservice.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("FROM Account a LEFT JOIN FETCH a.user u WHERE u.phoneNumber = ?1")
    List<Account> findAllByUserPhoneNumber(String phoneNumber);

    @Query("FROM Account a LEFT JOIN FETCH a.user WHERE a.accountNumber = ?1")
    Optional<Account> findByAccountNumber(String accountNumber);
}

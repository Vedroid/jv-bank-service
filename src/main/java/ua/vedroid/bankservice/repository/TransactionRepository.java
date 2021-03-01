package ua.vedroid.bankservice.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.vedroid.bankservice.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("FROM Transaction t JOIN FETCH t.fromAccount fa JOIN FETCH t.toAccount ta "
            + "WHERE fa.accountNumber = ?1 OR ta.accountNumber = ?1")
    List<Transaction> findAllByAccountNumber(String accountNumber, Pageable pageRequest);
}

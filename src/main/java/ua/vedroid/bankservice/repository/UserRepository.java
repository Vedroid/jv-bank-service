package ua.vedroid.bankservice.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.vedroid.bankservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    @Query(value = "FROM User u LEFT JOIN FETCH u.roles WHERE u.id = ?1")
    Optional<User> findById(Long id);

    @Query("FROM User u LEFT JOIN FETCH u.roles WHERE u.phoneNumber = ?1")
    Optional<User> findByPhoneNumber(String phoneNumber);
}

package ua.vedroid.bankservice.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.vedroid.bankservice.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> getByRoleName(Role.RoleName name);
}

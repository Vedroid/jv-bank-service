package ua.vedroid.bankservice.util;

import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ua.vedroid.bankservice.entity.Role;
import ua.vedroid.bankservice.service.RoleService;

@Component
@AllArgsConstructor
public class InjectComponent {
    private final RoleService roleService;

    @PostConstruct
    public void injectRoles() {
        Role roleAdmin = new Role();
        roleAdmin.setRoleName(Role.RoleName.ADMIN);
        roleService.save(roleAdmin);

        Role roleUser = new Role();
        roleUser.setRoleName(Role.RoleName.USER);
        roleService.save(roleUser);
    }
}

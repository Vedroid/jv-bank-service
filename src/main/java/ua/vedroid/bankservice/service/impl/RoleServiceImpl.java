package ua.vedroid.bankservice.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.vedroid.bankservice.entity.Role;
import ua.vedroid.bankservice.exception.NoEntityException;
import ua.vedroid.bankservice.repository.RoleRepository;
import ua.vedroid.bankservice.service.RoleService;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getByName(String name) {
        return roleRepository.getByRoleName(Role.RoleName.valueOf(name)).orElseThrow(() ->
                new NoEntityException("Role not found by name: " + name));
    }
}

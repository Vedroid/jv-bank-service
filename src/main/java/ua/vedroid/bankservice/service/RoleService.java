package ua.vedroid.bankservice.service;

import ua.vedroid.bankservice.entity.Role;

public interface RoleService {
    Role save(Role role);

    Role getByName(String name);
}

package ua.vedroid.bankservice.service.impl;

import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.vedroid.bankservice.entity.User;
import ua.vedroid.bankservice.exception.NoEntityException;
import ua.vedroid.bankservice.repository.UserRepository;
import ua.vedroid.bankservice.service.RoleService;
import ua.vedroid.bankservice.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleService roleService;

    @Override
    public User create(User user) {
        user.setRoles(Set.of(roleService.getByName("USER")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        user.setRoles(findById(user.getId()).getRoles());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new NoEntityException("User not found by Id: " + id));
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).orElseThrow(() ->
                new NoEntityException("User not found by number: " + phoneNumber));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}

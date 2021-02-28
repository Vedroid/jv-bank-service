package ua.vedroid.bankservice.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.vedroid.bankservice.entity.User;
import ua.vedroid.bankservice.exception.NoEntityException;
import ua.vedroid.bankservice.repository.UserRepository;
import ua.vedroid.bankservice.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
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
    public void remove(Long id) {
        userRepository.deleteById(id);
    }
}

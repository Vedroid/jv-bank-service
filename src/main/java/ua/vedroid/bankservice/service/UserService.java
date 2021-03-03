package ua.vedroid.bankservice.service;

import ua.vedroid.bankservice.entity.User;

public interface UserService {
    User create(User user);

    User update(User user);

    User findById(Long id);

    User findByPhoneNumber(String phoneNumber);

    void delete(Long id);
}

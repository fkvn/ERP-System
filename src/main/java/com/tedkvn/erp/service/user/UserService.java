package com.tedkvn.erp.service.user;

import com.tedkvn.erp.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();

    Optional<User> findByEmail(String e);

    Optional<User> findByPhoneAndPhoneRegion(String phone, String phoneRegion);

    Optional<User> findByUsername(String username);

    User save(User newUser);


    Optional<User> findByUsernameOrEmail(String usernameOrEmail);
}

package com.tedkvn.erp.service.user;

import com.tedkvn.erp.entity.user.User;
import com.tedkvn.erp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public List<User> findAllUsers() {
        return null;
        //        return userRepository.findByIsDeletedFalse();
    }

    @Override
    public Optional<User> findByEmail(String e) {
        return userRepository.findByEmail(e);
    }

    @Override
    public Optional<User> findByPhoneAndPhoneRegion(String phone, String phoneRegion) {
        return userRepository.findByPhoneAndPhoneRegion(phone, phoneRegion);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public Optional<User> findByUsernameOrEmail(String usernameOrEmail) {
        return userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
    }
}

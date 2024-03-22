package com.tedkvn.erp.service.user;

import com.tedkvn.erp.entity.User;
import com.tedkvn.erp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public List<User> findAllUsers() {
        return userRepository.findByIsDeletedFalse();
    }
}

package com.tedkvn.erp.service.user;

import com.tedkvn.erp.entity.User;
import com.tedkvn.erp.repository.UserRepository;
import com.tedkvn.erp.rest.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public List<User> findAllUsers() {
        if (userRepository.findById(Long.valueOf(5)).isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return userRepository.findByIsDeletedFalse();
    }
}

package com.tedkvn.erp.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl implements PasswordService {
    @Autowired
    private PasswordEncoder encoder;
    
    @Override
    public String encodePassword(String password) {
        return encoder.encode(password);
    }
}

package com.tedkvn.erp.service.user;

import com.tedkvn.erp.rest.exception.BadRequest;
import com.tedkvn.erp.rest.request.SignUpRequest;
import com.tedkvn.erp.util.PhoneUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public Long signUpUser(SignUpRequest request) {

        String email = request.getEmail();
        String password = request.getPassword();

        // assume phone and region is already being basic validated
        String phone = request.getPhone();
        String region = request.getRegion();
        if (!PhoneUtil.isPhoneValid(phone, region)) throw new BadRequest("Invalid Phone!");


        return null;
    }
}

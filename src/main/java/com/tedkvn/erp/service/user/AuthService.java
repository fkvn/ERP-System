package com.tedkvn.erp.service.user;

import com.tedkvn.erp.rest.request.SignUpRequest;

public interface AuthService {
    Long signUpUser(SignUpRequest request);
}

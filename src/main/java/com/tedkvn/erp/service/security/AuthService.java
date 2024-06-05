package com.tedkvn.erp.service.security;

import com.tedkvn.erp.entity.User;
import com.tedkvn.erp.rest.request.SignInByPassword;
import com.tedkvn.erp.rest.request.SignUpRequest;
import com.tedkvn.erp.rest.response.JwtResponse;

public interface AuthService {
    Long signUpUser(SignUpRequest request);

    JwtResponse signInByUsernameOrEmail(SignInByPassword request);

    JwtResponse signedJWTAuth(User user, String password);
}

package com.tedkvn.erp.rest.controller;

import com.tedkvn.erp.annotation.Authenticated;
import com.tedkvn.erp.rest.request.SignInByPassword;
import com.tedkvn.erp.rest.request.SignUpRequest;
import com.tedkvn.erp.rest.response.JwtResponse;
import com.tedkvn.erp.service.security.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;

// 30 secs
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    //    @Autowired
    //    PasswordEncoder encoder;

    @Autowired
    private AuthService authService;

    //    @PostMapping("/signingByPhone")
    //    @JsonView(View.Basic.class)
    //    public JwtResponse signingByPhone(@Valid @RequestBody SigningByPhoneRequest request) {
    //        return authService.signingWithThaiNowByPhone(request);
    //    }

    @PostMapping("/authz")
    @Authenticated
    public String checkAuthz() {
        return "Authz";
    }

    @PostMapping("/secret-base64-key")
    public String generateBase64EncodedSecretKey(
            @RequestParam(required = false, defaultValue = "32") int keySize) {
        byte[] key = new byte[keySize]; // Recommended key size for HMAC-SHA256 and HMAC-SHA512
        new SecureRandom().nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }

    @PostMapping("/signup")
    public Long signUpUser(@Valid @RequestBody SignUpRequest request) {
        return authService.signUpUser(request);
    }

    @PostMapping("/signin-usernameOrEmail")
    public JwtResponse signInByUsernameOrEmail(@Valid @RequestBody SignInByPassword request) {
        return authService.signInByUsernameOrEmail(request);
    }

}

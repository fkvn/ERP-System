package com.tedkvn.erp.rest.controller;

import com.tedkvn.erp.rest.exception.BadRequest;
import com.tedkvn.erp.rest.request.SignUpRequest;
import com.tedkvn.erp.service.user.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/signup")
    public Long signUpUser(@RequestBody SignUpRequest request) {
        throw new BadRequest("aaaa");
        //        return authService.signUpUser(request);
    }

}

package com.tedkvn.erp.rest.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


@Getter
public class SignInByPassword {

    @NotBlank(message = "sign in credential can't be blank. Credential could be email or username")
    private String usernameOrEmail;

    @NotBlank(message = "password can't be blank")
    //    @Password
    private String password;
}

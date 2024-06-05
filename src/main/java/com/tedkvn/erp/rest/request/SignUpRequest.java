package com.tedkvn.erp.rest.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


@Getter
public class SignUpRequest {

    private String email; // Optional

    @NotBlank(message = "username can't be blank")
    private String username;


    @NotBlank(message = "password can't be blank")
    //    @Password
    private String password;
}

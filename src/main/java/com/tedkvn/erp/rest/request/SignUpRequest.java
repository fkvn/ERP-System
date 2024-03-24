package com.tedkvn.erp.rest.request;

import com.tedkvn.erp.annotation.Password;
import com.tedkvn.erp.annotation.RegionCode;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


@Getter
public class SignUpRequest {

    private String email; // Optional

    private String username; // Optional

    @NotBlank(message = "phone number can't be blank")
    private String phone;

    @NotBlank(message = "region can't be blank")
    @RegionCode
    private String region;

    @NotBlank(message = "password can't be blank")
    @Password
    private String password;
}

package com.tedkvn.erp.rest.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


@Getter
public class CompanyRequest {

    @NotBlank(message = "Company name can't be blank")
    private String name;

    private String legalName; // Optional
}

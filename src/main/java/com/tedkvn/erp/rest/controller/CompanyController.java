package com.tedkvn.erp.rest.controller;

import com.tedkvn.erp.rest.request.CompanyRequest;
import com.tedkvn.erp.service.crm.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 30 secs
@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping
    public Long createCompany(@Valid @RequestBody CompanyRequest request) {
        return companyService.createCompany(request);
    }

}

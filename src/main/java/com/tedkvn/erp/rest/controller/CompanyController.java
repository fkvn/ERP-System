package com.tedkvn.erp.rest.controller;

import com.tedkvn.erp.entity.organization.Company;
import com.tedkvn.erp.service.crm.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 30 secs
@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping
    //    @RequiresAuthentication
    public List<Company> findAccessibleCompanies(@RequestParam Long accessibleToUserId) {
        return companyService.findAccessibleCompanies(accessibleToUserId);
    }
    
}

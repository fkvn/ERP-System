package com.tedkvn.erp.service.crm;

import com.tedkvn.erp.entity.organization.Company;
import com.tedkvn.erp.rest.request.CompanyRequest;

import java.util.List;

public interface CompanyService {
    List<Company> findAccessibleCompanies(Long accessibleToUserId);

    Long createCompany(CompanyRequest request);
}

package com.tedkvn.erp.service.crm;

import com.tedkvn.erp.entity.organization.Company;

import java.util.List;

public interface CompanyService {
    List<Company> findAccessibleCompanies(Long accessibleTo);
}

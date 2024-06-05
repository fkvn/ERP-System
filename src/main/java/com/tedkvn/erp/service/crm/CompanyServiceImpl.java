package com.tedkvn.erp.service.crm;

import com.tedkvn.erp.entity.organization.Company;
import com.tedkvn.erp.entity.privilege.AuthorityName;
import com.tedkvn.erp.repository.CompanyRepository;
import com.tedkvn.erp.rest.request.CompanyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public List<Company> findAccessibleCompanies(Long accessibleToUserId) {
        AuthorityName authority = AuthorityName.ACCESS_COMPANY_DATA;
        return new ArrayList<>();
        //        return companyRepository.findAccessibleCompanies(accessibleToUserId, authority);
    }

    @Override
    public Long createCompany(CompanyRequest request) {

        Company newCompany = new Company();
        newCompany.setName(request.getName());
        newCompany.setLegalName(request.getLegalName());

        return companyRepository.save(newCompany).getId();
    }
}

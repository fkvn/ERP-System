package com.tedkvn.erp.service.crm;

import com.tedkvn.erp.entity.organization.Company;
import com.tedkvn.erp.entity.privilege.AuthorityEnum;
import com.tedkvn.erp.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public List<Company> findAccessibleCompanies(Long accessibleTo) {
        AuthorityEnum authority = AuthorityEnum.ACCESS_COMPANY_DATA;

        return companyRepository.findAccessibleCompanies(accessibleTo, authority);
    }
}

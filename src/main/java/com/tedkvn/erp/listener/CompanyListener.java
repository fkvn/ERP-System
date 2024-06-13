package com.tedkvn.erp.listener;

import com.tedkvn.erp.entity.organization.Company;
import com.tedkvn.erp.util.DbUtil;
import jakarta.persistence.PrePersist;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CompanyListener {

    @PrePersist
    public void beforeCreateCompany(Company company) {
        Long nextSequence = DbUtil.getSequence(Company.class) + 1;

        String companyCode = String.valueOf(nextSequence);

        // Set the generated code on the company
        company.setCompanyCode(companyCode);

        // Update the sequence value in the database
        DbUtil.updateSequence(nextSequence, Company.class);
    }
}

package com.tedkvn.erp.listener;

import com.tedkvn.erp.entity.organization.Company;
import com.tedkvn.erp.rest.exception.SystemException;
import com.tedkvn.erp.util.BeanUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PrePersist;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CompanyListener {

    @PrePersist
    public void beforeCreateCompany(Company company) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);

        // Get the next sequence value
        try {
            Long nextValue = (Long) entityManager.createNativeQuery(
                    "SELECT current_value from company_code_sequence").getSingleResult() + 1;

            // Set the generated code on the company
            company.setCompanyCode(nextValue);

            // Update the sequence value in the database (optional)
            updateSequenceValue(nextValue);
        } catch (Exception e) {
            log.error("Company_code_sequence is not found!");
            throw new SystemException();
        }
    }


    private void updateSequenceValue(Long value) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        entityManager.createNativeQuery(
                        "UPDATE company_code_sequence SET current_value = :newValue")
                .setParameter("newValue", value).executeUpdate();
    }

}

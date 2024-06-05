package com.tedkvn.erp.repository;

import com.tedkvn.erp.entity.organization.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    //    @Query("SELECT c FROM Company c JOIN UserCompany uc ON c.id = uc.company.id " +
    //            // Join Company and UserCompany
    //            "WHERE uc.user.id = :userId AND EXISTS (SELECT 1 FROM UserRole ur " +
    //            // Access User through UserRole
    //            "WHERE ur.user IN (SELECT u FROM User u WHERE u.id = :userId) " +
    //            // Access Authority through role
    //            "AND EXISTS (SELECT 1 FROM ur.role.authorities a WHERE a.name = :authorityName))")
    //    List<Company> findAccessibleCompanies(Long userId,
    //                                          @Param("authorityName") AuthorityName
    //                                          authorityName);
}

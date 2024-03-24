package com.tedkvn.erp.entity.privilege;

import com.tedkvn.erp.entity.User;
import com.tedkvn.erp.entity.organization.Company;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "user_company")
@Getter
@Setter
@ToString
public class UserCompany implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Adjust if needed
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Company company;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<UserRole> userRoles;
}

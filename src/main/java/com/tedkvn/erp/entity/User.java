package com.tedkvn.erp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tedkvn.erp.audit.AbstractBasicAuditable;
import com.tedkvn.erp.entity.organization.Company;
import com.tedkvn.erp.entity.privilege.UserRole;
import com.tedkvn.erp.listener.UserList ener;
import com.tedkvn.erp.listener.UserListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
@EntityListeners(UserListener.class)
@Indexed
public class User extends AbstractBasicAuditable implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Sub is used to generate and authenticate JWT Token
    private String sub = UUID.randomUUID().toString();

    // user sequence code
    private Long userCode;

    @JsonIgnore
    private String password = "";

    private String username = "";
    private String fullName = "";
    private String email = "";
    private String phone = "";
    private String phoneRegion = "US";
    private String note = "";

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    private boolean isSuperAdmin = false;

    @ManyToMany
    private Set<Company> companies = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<UserRole> userRoles = new ArrayList<>();
}
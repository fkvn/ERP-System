package com.tedkvn.erp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tedkvn.erp.audit.AbstractBasicAuditable;
import com.tedkvn.erp.entity.organization.Company;
import com.tedkvn.erp.entity.privilege.UserRole;
import com.tedkvn.erp.listener.UserListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

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
    @GenericField(sortable = Sortable.YES)
    private Long id;

    // Sub is used to generate and authenticate JWT Token
    @JsonIgnore
    private String sub = UUID.randomUUID().toString();

    // sequence code -> auto-generated via the EntityListener
    @GenericField(sortable = Sortable.YES)
    private String userCode;

    @JsonIgnore
    private String password = "";

    @FullTextField
    private String username = "";

    @FullTextField
    private String fullName = "";
    @GenericField(sortable = Sortable.YES)
    private String email = "";
    @FullTextField
    private String phone = "";
    private String phoneRegion = "US";
    private String note = "";

    @Enumerated(EnumType.STRING)
    @KeywordField
    private UserStatus status = UserStatus.ACTIVE;

    private boolean isSuperAdmin = false;

    @ManyToMany
    private Set<Company> companies = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<UserRole> userRoles = new ArrayList<>();
}
package com.tedkvn.erp.entity.organization;

import com.tedkvn.erp.audit.AbstractBasicAuditable;
import com.tedkvn.erp.entity.privilege.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "store")
@Getter
@Setter
@ToString
public class Store extends AbstractBasicAuditable implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name = "";

    @ManyToOne
    private Company company = null;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Set<UserRole> userRoles = new HashSet<>();

}

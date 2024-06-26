package com.tedkvn.erp.entity.organization;

import com.tedkvn.erp.entity.AbstractBasicAuditable;
import com.tedkvn.erp.entity.user.User;
import com.tedkvn.erp.listener.CompanyListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "company")
@Getter
@Setter
@ToString
@EntityListeners(CompanyListener.class)
public class Company extends AbstractBasicAuditable implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // sequence code -> auto-generated via the EntityListener
    private String companyCode = "";

    private String name = "";

    private String legalName = "";

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "companies", cascade = CascadeType.DETACH)
    private Set<User> users = new HashSet<>(); // Users associated with the company

    @OneToMany(mappedBy = "company", cascade = CascadeType.DETACH) // One-to-many with Store
    private Set<Store> stores = new HashSet<>(); // Collection of Stores

    @OneToMany(mappedBy = "company", cascade = CascadeType.DETACH) // One-to-many with Warehouse
    private Set<Warehouse> warehouses = new HashSet<>(); // Collection of Warehouses
}

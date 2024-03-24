package com.tedkvn.erp.entity.organization;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tedkvn.erp.entity.privilege.UserCompany;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "company")
@Getter
@Setter
@ToString

public class Company implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String legalName;

    @CreationTimestamp
    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime createdOn;

    @UpdateTimestamp
    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime updatedOn;

    @JsonIgnore
    private boolean isDeleted = false; // Soft delete flag

    @JsonIgnore
    private LocalDateTime deleteOn; // Timestamp of soft deletion

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "company", cascade = CascadeType.DETACH)
    private Set<UserCompany> userCompanies; // Users associated with the company

    @OneToMany(mappedBy = "company", cascade = CascadeType.DETACH) // One-to-many with Store
    private Set<Store> stores; // Collection of Stores

    @OneToMany(mappedBy = "company", cascade = CascadeType.DETACH) // One-to-many with Warehouse
    private Set<Warehouse> warehouses; // Collection of Warehouses
}

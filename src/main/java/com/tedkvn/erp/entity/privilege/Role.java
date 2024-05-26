package com.tedkvn.erp.entity.privilege;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "role")
@Getter
@Setter
@ToString
public class Role implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName name; // Use RoleEnum for strong typing

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles") // Many-to-many with Authority
    private Set<Authority> authorities;

}
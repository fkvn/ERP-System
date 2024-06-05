package com.tedkvn.erp.entity.privilege;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authority")
@Getter
@Setter
@ToString
public class Authority implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AuthorityName name = null;

    @ManyToMany // Many-to-many with Role
    private Set<Role> roles = new HashSet<>(); // Roles associated with the authority
}
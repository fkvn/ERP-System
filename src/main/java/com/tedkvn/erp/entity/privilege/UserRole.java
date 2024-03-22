package com.tedkvn.erp.entity.privilege;

import com.tedkvn.erp.entity.User;
import com.tedkvn.erp.entity.organization.Store;
import com.tedkvn.erp.entity.organization.Warehouse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "user_role")
@Getter
@Setter
@ToString
public class UserRole implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Role role;

    @ManyToOne // Optional, if roles are specific to stores
    private Store store;

    @ManyToOne // Optional, if roles are specific to warehouses
    private Warehouse warehouse;
}

package com.tedkvn.erp.entity.organization;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tedkvn.erp.entity.privilege.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "store")
@Getter
@Setter
@ToString
public class Store implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

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

    @ManyToOne
    private Company company;

    @ManyToMany(cascade = CascadeType.DETACH)
    private Set<UserRole> userRoles = new HashSet<>();

}

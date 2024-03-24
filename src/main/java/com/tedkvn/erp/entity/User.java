package com.tedkvn.erp.entity;

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
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sub = UUID.randomUUID().toString();

    private String username;

    @JsonIgnore
    private String password;

    private String email; // Optional attribute
    private String phone;
    private String phoneRegion;

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<UserCompany> companies;
}
package com.tedkvn.erp.audit;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass // Mark it for inheritance by entities
@Getter
@Setter
public abstract class AbstractBasicAuditable {
    @CreationTimestamp
    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime createdOn;

    @Column(insertable = false) // won't be triggered for creation.
    @UpdateTimestamp
    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime updatedOn;

    private String createdBy;
    private String updatedBy;
}
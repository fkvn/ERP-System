package com.tedkvn.erp.audit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tedkvn.erp.listener.BasicAuditListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass // Mark it for inheritance by entities
@Getter
@Setter
@EntityListeners(BasicAuditListener.class)
public abstract class AbstractBasicAuditable {

    private String createdBy = "anonymousUser";

    @CreationTimestamp
    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime createdOn;

    @Column(insertable = false) // won't be triggered for creation.
    private String updatedBy = "anonymousUser";

    @Column(insertable = false) // won't be triggered for creation.
    @UpdateTimestamp
    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime updatedOn;
}
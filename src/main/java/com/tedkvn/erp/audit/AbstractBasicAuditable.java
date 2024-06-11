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
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import java.time.LocalDateTime;

@MappedSuperclass // Mark it for inheritance by entities
@Getter
@Setter
@EntityListeners(BasicAuditListener.class)
@Indexed
public abstract class AbstractBasicAuditable {

    @FullTextField
    private String createdBy = "";

    @CreationTimestamp
    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    @GenericField(sortable = Sortable.YES)
    private LocalDateTime createdOn;

    @FullTextField
    @Column(insertable = false) // won't be triggered for creation.
    private String updatedBy = "";

    @Column(insertable = false) // won't be triggered for creation.
    @UpdateTimestamp
    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    @GenericField(sortable = Sortable.YES)
    private LocalDateTime updatedOn;
}
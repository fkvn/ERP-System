package com.tedkvn.erp.listener;

import com.tedkvn.erp.audit.AbstractBasicAuditable;
import com.tedkvn.erp.util.AuthUtil;
import com.tedkvn.erp.util.BeanUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class BasicAuditListener {

    @PrePersist
    public void preCreateEntity(AbstractBasicAuditable entity) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        entity.setCreatedOn(LocalDateTime.now());
        entity.setCreatedBy(AuthUtil.getAuthenticatorUsername());
    }

    @PreUpdate
    public void preUpdateEntity(AbstractBasicAuditable entity) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        entity.setUpdatedOn(LocalDateTime.now());
        entity.setUpdatedBy(AuthUtil.getAuthenticatorUsername());
    }
}

package com.tedkvn.erp.listener;

import com.tedkvn.erp.entity.User;
import com.tedkvn.erp.util.AuthUtil;
import com.tedkvn.erp.util.BeanUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PrePersist;
import org.springframework.stereotype.Component;

@Component
public class UserListener {

    @PrePersist
    public void beforeCreateUser(User user) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);

        // Get the next sequence value
        Long nextValue = (Long) entityManager.createNativeQuery(
                "SELECT current_value from user_code_sequence").getSingleResult() + 1;

        // Set the generated code on the user
        user.setUserCode(nextValue);

        // Update the sequence value in the database (optional)
        updateSequenceValue(nextValue);

        // Set the creator name on the user
        user.setCreatedBy(AuthUtil.getAuthenticatorUsername());
    }


    private void updateSequenceValue(Long value) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        entityManager.createNativeQuery("UPDATE user_code_sequence SET current_value = :newValue")
                .setParameter("newValue", value).executeUpdate();
    }

}

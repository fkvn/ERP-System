package com.tedkvn.erp.listener;

import com.tedkvn.erp.entity.User;
import com.tedkvn.erp.rest.exception.SystemException;
import com.tedkvn.erp.util.BeanUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PrePersist;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserListener {

    @PrePersist
    public void beforeCreateUser(User user) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);

        // Get the next sequence value
        try {
            Long nextValue = (Long) entityManager.createNativeQuery(
                    "SELECT current_value from user_code_sequence").getResultList().getFirst() + 1;

            // Set the generated code on the user
            user.setUserCode(nextValue);

            // Update the sequence value in the database (optional)
            updateSequenceValue(nextValue);
        } catch (Exception e) {
            log.error("User_code_sequence is not found!");
            throw new SystemException();

        }
    }


    private void updateSequenceValue(Long value) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        entityManager.createNativeQuery("UPDATE user_code_sequence SET current_value = :newValue")
                .setParameter("newValue", value).executeUpdate();
    }

}

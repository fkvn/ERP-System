package com.tedkvn.erp.listener;

import com.tedkvn.erp.config.AbstractInitSequenceBuild;
import com.tedkvn.erp.entity.User;
import com.tedkvn.erp.util.DbUtil;
import jakarta.persistence.PrePersist;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserListener extends AbstractInitSequenceBuild {

    @PrePersist
    public void beforeCreateUser(User user) {

        Long nextValue = DbUtil.getSequence(User.class) + 1;

        String userCode = String.valueOf(nextValue);

        // Set the generated code on the user
        user.setUserCode(userCode);

        // Update the sequence value in the database
        DbUtil.updateSequence(nextValue, User.class);
    }

    @Override
    protected Class<?> getSpecificClass() {
        return User.class;
    }
}

package com.tedkvn.erp.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DbUtil {
    public static Long getSequence(Class<?> c) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        String table = (c.getSimpleName() + "_code_sequence ").toLowerCase();
        Query query = entityManager.createNativeQuery("SELECT current_value from " + table);
        return (Long) query.getResultList().getFirst();
    }

    public static void updateSequence(Long sequence, Class<?> c) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        String table = (c.getSimpleName() + "_code_sequence ").toLowerCase();
        Query query = entityManager.createNativeQuery(
                "UPDATE " + table + " SET current_value" + " =" + " :newValue");
        query.setParameter("newValue", sequence).executeUpdate();
    }
}

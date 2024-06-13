package com.tedkvn.erp.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DbUtil {

    public static void initSequence(Class<?> c) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        String table = (c.getSimpleName() + "_code_sequence ").toLowerCase();

        String createTableSql =
                "CREATE TABLE IF NOT EXISTS " + table + " ( id INT NOT NULL " + "AUTO_INCREMENT " +
                        "PRIMARY KEY, current_value BIGINT NOT NULL)";

        Query query = entityManager.createNativeQuery(createTableSql);
        query.executeUpdate();

        try {
            // if sequence is existed -> do nothing
            getSequence(c);
        } catch (Exception e) {
            // insert if no sequence is existed
            String insertSql = "INSERT INTO " + table + " (current_value) VALUES (100000)";
            query = entityManager.createNativeQuery(insertSql);
            query.executeUpdate();
        }
    }

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

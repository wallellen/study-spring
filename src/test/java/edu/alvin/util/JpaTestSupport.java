package edu.alvin.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JpaTestSupport {

    @Autowired
    protected ApplicationContext ac;

    @PersistenceContext
    protected EntityManager em;

    @Autowired
    private PlatformTransactionManager tm;

    protected void clearDb(String...tableNames) {
        TransactionStatus tx = tm.getTransaction(null);
        try {
            for (String tableName : tableNames) {
                em.createNativeQuery("truncate table " + tableName).executeUpdate();
            }
            tm.commit(tx);
        } catch (Exception e) {
            tm.rollback(tx);
        }
    }

    protected <T extends Builder> T withBuilder(Class<T> builderType) {
        return ac.getBean(builderType);
    }
}

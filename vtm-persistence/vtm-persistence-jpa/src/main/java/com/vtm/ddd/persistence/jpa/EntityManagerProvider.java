package com.vtm.ddd.persistence.jpa;


import com.vtm.ddd.domain.InstanceFactory;
import com.vtm.ddd.domain.IocInstanceNotFoundException;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * JPA 实体管理器提供者。如果当前线程中尚未存在entityManager线程变量，则从IoC容器中获取一个并存入当前线程，
 * 如果当前线程已经存在entityManager线程变量，直接返回。
 * <p>
 * 本类的存在，主要是为了在当前线程中，每次请求都返回相同的entityManager对象。避免事务和“会话已关闭”问题。
 *
<<<<<<< HEAD:vtm-persistence/vtm-persistence-jpa/src/main/java/com/vtm/persistence/jpa/EntityManagerProvider.java
 * @author
=======
 * @author jony
>>>>>>> 9c046b3f97c1e6d4438ccaf4ef5ad67749ca193e:vtm-persistence/vtm-persistence-jpa/src/main/java/com/vtm/ddd/persistence/jpa/EntityManagerProvider.java
 */
@Named
public class EntityManagerProvider {

    private EntityManagerFactory entityManagerFactory;

    private final ThreadLocal<EntityManager> entityManagerHolder = new ThreadLocal<EntityManager>();

    public EntityManagerProvider() {
    }

    public EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = InstanceFactory.getInstance(EntityManagerFactory.class);
        }
        return entityManagerFactory;
    }

    public EntityManagerProvider(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public EntityManagerProvider(EntityManager entityManager) {
        entityManagerHolder.set(entityManager);
    }

    public EntityManager getEntityManager() {
        EntityManager result = entityManagerHolder.get();
        if (result != null && result.isOpen()) {
            return result;
        }
        result = getEntityManagerFromIoC();
        entityManagerHolder.set(result);
        return result;
    }

    public EntityManager getEntityManagerFromIoC() {
        try {
            return InstanceFactory.getInstance(EntityManager.class);
        } catch (IocInstanceNotFoundException e) {
            if (entityManagerFactory == null) {
                entityManagerFactory = InstanceFactory.getInstance(EntityManagerFactory.class);
            }
            return entityManagerFactory.createEntityManager();
        }
    }
}

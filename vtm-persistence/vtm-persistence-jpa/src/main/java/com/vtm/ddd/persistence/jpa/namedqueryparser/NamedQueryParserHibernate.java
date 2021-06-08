package com.vtm.ddd.persistence.jpa.namedqueryparser;


import com.vtm.ddd.persistence.jpa.EntityManagerProvider;
import com.vtm.ddd.persistence.jpa.NamedQueryParser;
import org.hibernate.Session;

import javax.inject.Named;

/**
 * NamedQueryParser接口的Hibernate实现
 *
<<<<<<< HEAD:vtm-persistence/vtm-persistence-jpa/src/main/java/com/vtm/persistence/jpa/namedqueryparser/NamedQueryParserHibernate.java
 * @author
=======
 * @author jony
>>>>>>> 9c046b3f97c1e6d4438ccaf4ef5ad67749ca193e:vtm-persistence/vtm-persistence-jpa/src/main/java/com/vtm/ddd/persistence/jpa/namedqueryparser/NamedQueryParserHibernate.java
 */
@Named
public class NamedQueryParserHibernate extends NamedQueryParser {

    public NamedQueryParserHibernate() {
    }

    public NamedQueryParserHibernate(EntityManagerProvider entityManagerProvider) {
        super(entityManagerProvider);
    }

    @Override
    public String getQueryStringOfNamedQuery(String queryName) {
        Session session = (Session) getEntityManager().getDelegate();
        return session.getNamedQuery(queryName).getQueryString();
    }

}

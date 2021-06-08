package com.vtm.ddd.querychannel.query;


import com.vtm.ddd.domain.BaseQuery;
import com.vtm.ddd.domain.EntityRepository;
import com.vtm.ddd.domain.JpqlQuery;
import com.vtm.ddd.querychannel.ChannelQuery;
import com.vtm.ddd.utils.Assert;
import com.vtm.ddd.utils.Page;

import java.util.List;

/**
 * 通道查询的JPQL实现
 *
 * @author jony
 */
public class ChannelJpqlQuery extends ChannelQuery<ChannelJpqlQuery> {

    private final JpqlQuery query;

    public ChannelJpqlQuery(EntityRepository repository, String jpql) {
        super(repository);
        Assert.notBlank(jpql, "JPQL must be set!");
        query = new JpqlQuery(repository, jpql);
        setQuery(query);
    }

    @Override
    public <T> List<T> list() {
        return query.list();
    }

    @Override
    public <T> Page<T> pagedList() {
        return new Page<T>(query.getFirstResult(), queryResultCount(),
                query.getMaxResults(), (List<T>) query.list());
    }

    @Override
    public <T> T singleResult() {
        return (T) query.singleResult();
    }

    @Override
    protected String getQueryString() {
        return query.getJpql();
    }

    @Override
    protected BaseQuery createBaseQuery(String queryString) {
        return repository.createJpqlQuery(queryString);
    }

}

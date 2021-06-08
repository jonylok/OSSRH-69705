package com.vtm.ddd.querychannel.impl;


import com.vtm.ddd.domain.EntityRepository;
import com.vtm.ddd.domain.InstanceFactory;
import com.vtm.ddd.querychannel.ChannelQuery;
import com.vtm.ddd.querychannel.QueryChannelService;
import com.vtm.ddd.utils.Assert;
import com.vtm.ddd.utils.Page;
import com.vtm.ddd.querychannel.query.ChannelJpqlQuery;
import com.vtm.ddd.querychannel.query.ChannelNamedQuery;
import com.vtm.ddd.querychannel.query.ChannelSqlQuery;

import javax.inject.Named;
import java.util.List;

/**
 *
 * @author jony
 */
@Named
public class QueryChannelServiceImpl implements QueryChannelService {
    
    private EntityRepository repository;

    public QueryChannelServiceImpl(EntityRepository repository) {
        Assert.notNull(repository, "Repository must set!");
        this.repository = repository;
    }

    public EntityRepository getRepository() {
        if(repository == null){
            repository = InstanceFactory.getInstance(EntityRepository.class);
        }
        return repository;
    }

    @Override
    public ChannelJpqlQuery createJpqlQuery(String jpql) {
        return new ChannelJpqlQuery(getRepository(), jpql);
    }

    @Override
    public ChannelNamedQuery createNamedQuery(String queryName) {
        return new ChannelNamedQuery(getRepository(), queryName);
    }

    @Override
    public ChannelSqlQuery createSqlQuery(String sql) {
        return new ChannelSqlQuery(getRepository(), sql);
    }

    @Override
    public <T> List<T> list(ChannelQuery query) {
        return query.list();
    }

    @Override
    public <T> Page<T> pagedList(ChannelQuery query) {
        return query.pagedList();
    }

    @Override
    public <T> T getSingleResult(ChannelQuery query) {
        return (T) query.singleResult();
    }

    @Override
    public long getResultCount(ChannelQuery query) {
        return query.queryResultCount();
    }
    
}

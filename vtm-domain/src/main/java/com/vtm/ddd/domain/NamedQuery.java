package com.vtm.ddd.domain;


import com.vtm.ddd.utils.Assert;

import java.util.List;

/**
 * 基于命名查询的查询对象。DDD支持的四种查询形式之一。
 * 可以指定定位查询参数或命名查询参数，也可以针对查询结果取子集。
 * @author jony
 */
public class NamedQuery extends BaseQuery<NamedQuery> {

    private final String queryName;

    /**
     * 使用仓储和命名查询名字创建命名查询
     * @param repository 仓储
     * @param queryName 命名查询的名称
     */
    public NamedQuery(EntityRepository repository, String queryName) {
        super(repository);
        Assert.notBlank(queryName);
        this.queryName = queryName;
    }

    /**
     * 获取命名查询的名称
     * @return 命名查询名称
     */
    public String getQueryName() {
        return queryName;
    }

    /**
     * 返回查询结果列表。
     * @param <T> 查询结果的列表元素类型
     * @return 查询结果。
     */
    @Override
    public <T> List<T> list() {
        return getRepository().find(this);
    }

    /**
     * 返回单条查询结果。
     * @param <T> 查询结果的类型
     * @return 查询结果。
     */
    @Override
    public <T> T singleResult() {
        return getRepository().getSingleResult(this);
    }

    /**
     * 执行更新仓储的操作。
     * @return 被更新或删除的实体的数量
     */
    @Override
    public int executeUpdate() {
        return getRepository().executeUpdate(this);
    }

}

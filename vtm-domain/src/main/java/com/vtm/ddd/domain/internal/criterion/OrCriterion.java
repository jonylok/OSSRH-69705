package com.vtm.ddd.domain.internal.criterion;

import com.vtm.ddd.domain.NamedParameters;
import com.vtm.ddd.domain.QueryCriterion;
import com.vtm.ddd.utils.Assert;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;


/**
 * 代表两个或两个以上的查询条件OR操作结果的查询条件
 *
 * @author jony
 */
public class OrCriterion extends AbstractCriterion {

    private final List<QueryCriterion> criteria;

    /**
     * 根据多个查询条件创建OR查询条件。创建过程中会去除为null或EmptyCriterion的查询条件。如果
     * 剩余查询条件不足两个，则抛出异常。
     *
     * @param criteria 要用来执行OR操作的查询条件
     */
    public OrCriterion(QueryCriterion... criteria) {
        Assert.notNull(criteria, "Criteria to \"OR\" is null!");
        this.criteria = removeNullOrEmptyCriterion(criteria);
        Assert.isTrue(criteria.length > 1, "At least two query criteria required!");
    }

    /**
     * 获得要用来执行OR操作的查询条件
     *
     * @return 要用来执行AND操作的查询条件，去除了Null和EmptyCriterion类型的元素。
     */
    public List<QueryCriterion> getCriteria() {
        return criteria;
    }

    @Override
    public String toQueryString() {
        List<String> subCriteriaStr = new ArrayList<String>();
        for (QueryCriterion each : getCriteria()) {
            subCriteriaStr.add(each.toQueryString());
        }
        return "(" + StringUtils.join(subCriteriaStr, " or ") + ")";
    }

    @Override
    public NamedParameters getParameters() {
        NamedParameters result = NamedParameters.create();
        for (QueryCriterion each : getCriteria()) {
            result.add(each.getParameters());
        }
        return result;
    }

    /**
     * 判断等价性
     *
     * @param other 要用来判等的另一个对象
     * @return 如果当前对象和other等价，则返回true，否则返回false
     */
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OrCriterion)) {
            return false;
        }
        OrCriterion that = (OrCriterion) other;
        return (this.criteria.containsAll(that.criteria)) && that.criteria.containsAll(this.criteria);
    }

    /**
     * 计算哈希值
     *
     * @return 当前对象实例的哈希值
     */
    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder(17, 37);
        for (QueryCriterion criterion : criteria) {
            builder = builder.append(criterion);
        }
        return builder.toHashCode();
    }
}

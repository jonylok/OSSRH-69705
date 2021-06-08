package com.vtm.ddd.domain.internal.criterion;

/**
 * 代表属性小于或等于指定值的查询条件
 * @author jony
 */
public class GeCriterion extends ValueCompareCriterion {

    public GeCriterion(String propName, Comparable<?> value) {
        super(propName, value);
        setOperator(" >= ");
    }
}

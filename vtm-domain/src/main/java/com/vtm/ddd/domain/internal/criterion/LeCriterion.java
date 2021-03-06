package com.vtm.ddd.domain.internal.criterion;

/**
 * 代表属性小于或等于指定值的查询条件
 * @author jony
 */
public class LeCriterion extends ValueCompareCriterion {

    public LeCriterion(String propName, Comparable<?> value) {
        super(propName, value);
        setOperator(" <= ");
    }
}

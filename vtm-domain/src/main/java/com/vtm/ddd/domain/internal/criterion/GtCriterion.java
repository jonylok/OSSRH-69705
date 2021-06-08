package com.vtm.ddd.domain.internal.criterion;

/**
 * 代表属性大于指定值的查询条件
 * @author jony
 */
public class GtCriterion extends ValueCompareCriterion {

    public GtCriterion(String propName, Comparable<?> value) {
        super(propName, value);
        setOperator(" > ");
    }
}

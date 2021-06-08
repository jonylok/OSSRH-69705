package com.vtm.ddd.domain.internal.criterion;

/**
 * 代表属性小于指定值的查询条件
 * @author jony
 */
public class LtCriterion extends ValueCompareCriterion {

	public LtCriterion(String propName, Comparable<?> value) {
        super(propName, value);
        setOperator(" < ");
    }
}

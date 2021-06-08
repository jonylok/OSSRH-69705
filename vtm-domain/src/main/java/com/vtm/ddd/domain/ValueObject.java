package com.vtm.ddd.domain;

import java.io.Serializable;

/**
 * 领域模型中的值对象的标识接口。便于对系统中的值对象进行统一处理。所有值对象都应该直接或间接实现此接口。
 * @author jony
 *
 */
public interface ValueObject extends Serializable {
}

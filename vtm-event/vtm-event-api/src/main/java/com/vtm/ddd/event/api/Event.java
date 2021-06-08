package com.vtm.ddd.event.api;

import java.util.Date;

/**
 * @Company:
 * @Description:
 * @Author: yizicheng
 * @Version:v1.0
 * @Date:2020/6/28 6:08 PM
 **/
public interface Event {
    /**
     * 获得事件ID
     *
     * @return 事件的ID
     */
    String id();

    /**
     * 获得事件发生时间
     *
     * @return 事件发生时间
     */
    Date occurredOn();

    /**
     * 获得版本
     *
     * @return 事件的版本
     */
    int version();
}

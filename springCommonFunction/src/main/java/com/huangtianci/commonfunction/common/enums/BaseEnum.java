package com.huangtianci.commonfunction.common.enums;

/**
 * @author Huang Tianci
 * @date 2017/11/23
 * 所有枚举类都要实现这个接口
 */
public interface BaseEnum<T extends Enum<T> & BaseEnum<T>> {

    /**
     * code
     * @return code
     */
    String getCode();

    /**
     * desc
     * @return desc
     */
    String getDesc();
}

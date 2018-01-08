package com.huangtianci.commonfunction.common.enums;

/**
 * @author Huang Tianci
 * @date 2017/11/23
 * 返回编码枚举类
 */
public enum ResultCodeEnum implements BaseEnum<ResultCodeEnum> {

    SUCCESS("200", "成功"),
    FAIL("500", "系统异常");

    private String code;
    private String desc;

    ResultCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}

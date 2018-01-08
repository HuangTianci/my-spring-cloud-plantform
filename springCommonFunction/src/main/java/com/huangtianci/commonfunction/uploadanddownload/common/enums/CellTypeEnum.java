package com.huangtianci.commonfunction.uploadanddownload.common.enums;

import com.huangtianci.commonfunction.common.enums.BaseEnum;

/**
 * @author Huang Tianci
 * @date 2017/11/25
 */
public enum CellTypeEnum implements BaseEnum {
    STRING("string", "string"),
    NUMBER("number", "number"),
    BOOLEAN("boolean", "boolean"),
    DATE("date", "date"),
    BIGDECIMAL("bigdecimal", "bigdecimal"),
    ;

    private String code;

    private String desc;

    CellTypeEnum(String code, String desc) {
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

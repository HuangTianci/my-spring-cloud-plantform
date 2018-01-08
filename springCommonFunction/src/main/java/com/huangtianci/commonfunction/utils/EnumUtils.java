package com.huangtianci.commonfunction.utils;


import com.huangtianci.commonfunction.common.enums.BaseEnum;

/**
 * @author Huang Tianci
 * @date 2017/11/25
 */
public class EnumUtils {
	
	public static BaseEnum getEnumByCode(Class<? extends BaseEnum> type, String code) {
		BaseEnum[] baseEnums = type.getEnumConstants();
		for (BaseEnum baseEnum : baseEnums) {
			if (code.equalsIgnoreCase(baseEnum.getCode())) {
				return baseEnum;
			}
		}
		return null;
	}

}

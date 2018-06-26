package com.huangtianci.commonfunction.common.exception;

/**
 * @author Huang Tianci
 * @date 2017/11/28
 * 参数异常
 */
public class ParameterException extends RuntimeException {

    public ParameterException(String msg) {
        super(msg);
    }

    public ParameterException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}

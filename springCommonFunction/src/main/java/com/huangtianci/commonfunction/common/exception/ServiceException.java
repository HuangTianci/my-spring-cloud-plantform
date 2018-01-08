package com.huangtianci.commonfunction.common.exception;

/**
 * @author Huang Tianci
 * @date 2017/11/23
 * 服务异常
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

}

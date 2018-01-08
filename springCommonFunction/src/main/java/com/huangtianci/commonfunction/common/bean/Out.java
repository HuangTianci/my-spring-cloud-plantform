package com.huangtianci.commonfunction.common.bean;

import com.huangtianci.commonfunction.common.enums.ResultCodeEnum;

import java.io.Serializable;

/**
 * @author Huang Tianci
 * @date 2017/11/23
 * 输出类
 */
public class Out<T> implements Serializable{

    private boolean isSuccess = false;
    private String  message;
    private String  code;
    private T       result;

    public Out() {
        super();
    }

    public Out(T result) {
        super();
        this.isSuccess = true;
        this.result = result;
    }

    public Out(String message, String code) {
        super();
        this.isSuccess = false;
        this.message = message;
        this.code = code;
    }

    public Out(boolean success, String message, String code) {
        super();
        this.isSuccess = success;
        this.message = message;
        this.code = code;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public static <T> Builder<T> builder() {
        return new Builder<T>();
    }

    public static class Builder<T> {

        private T result;

        private boolean isSuccess = false;

        private String  message;

        private String  code;

        public Builder<T> result(T result) {
            this.result = result;
            return this;
        }

        public Builder<T> success() {
            this.isSuccess = true;
            this.code = ResultCodeEnum.SUCCESS.getCode();
            this.message = ResultCodeEnum.SUCCESS.getDesc();
            return this;
        }

        public Builder<T> success(String message) {
            this.isSuccess = true;
            this.code = ResultCodeEnum.SUCCESS.getCode();
            this.message = message;
            return this;
        }

        public Builder<T> fail(String code, String message) {
            this.isSuccess = false;
            this.code = code;
            this.message = message;
            return this;
        }

        public Builder<T> fail(ResultCodeEnum errorCodeEnum) {
            this.isSuccess = false;
            this.code = errorCodeEnum.getCode();
            this.message = errorCodeEnum.getDesc();
            return this;
        }

        public Builder<T> fail(Out out) {
            this.isSuccess = false;
            this.code = out.getCode();
            this.message = out.getMessage();
            return this;
        }

        public Out<T> build() {
            Out<T> out = new Out<T>();
            out.setSuccess(this.isSuccess);
            out.setCode(this.code);
            out.setMessage(this.message);
            out.setResult(this.result);
            return out;
        }
    }
}

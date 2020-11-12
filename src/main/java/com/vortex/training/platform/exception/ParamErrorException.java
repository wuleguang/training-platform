package com.vortex.training.platform.exception;

/**
 * @author light
 * @since 2019/6/6
 */
public class ParamErrorException extends Exception {

    private final Integer code = 400;

    private String message;

    public ParamErrorException(String message) {
        this.message = "[参数错误异常]" + message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

package com.vortex.training.platform.constants;

/**
 * @author : light
 * @date: 2020/10/30 16:36
 */
public enum ResultCode {
    SUCCESS(200, "success"),
    PARAM_IS_BLANK(1001, "Param is blank"),
    SERVER_ERROR(500, "Internal Server Error");

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

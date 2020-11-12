package com.vortex.training.platform.dto;

/**
 * @author : light
 * @date: 2020/10/30 11:16
 */
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "公共相应参数")
public class Result<T> implements Serializable {

    public static final int SUCCESS = 0;
    public static final int FAILED = 1;

    public static boolean isSuccess(Result result) {
        return result.getRc() == SUCCESS;
    }

    public static <T> Result<T> newFailed() {
        return newResult(FAILED, null, null);
    }

    public static <T> Result<T> newFailed(String err) {
        return newResult(FAILED, null, err);
    }

    public static <T> Result<T> newFailed(int rc, String err) {
        return newResult(rc, null, err);
    }

    public static <T> Result<T> newSuccess() {
        return newResult(SUCCESS, null, null);
    }

    public static <T> Result<T> newSuccess(T t) {
        return newResult(SUCCESS, t, null);
    }

    public static <T> Result<T> newSuccess(int rc, T t) {
        return newResult(rc, t, null);
    }

    private static <T> Result<T> newResult(int rc, T t, String err) {
        return new Result<T>(rc, t, err);
    }

    /**
     * 返回码
     */
    @ApiModelProperty(value = "返回码")
    private int rc;

    /**
     * 正确时的返回结果
     */
    @ApiModelProperty(value = "正确时的返回结果")
    private T ret;

    /**
     * 失败时的异常信息
     */
    @ApiModelProperty(value = "失败时的异常信息")
    private String err;


    private Result() {
    }

    private Result(int rc, String err) {
        this(rc, null, err);
    }

    private Result(int rc, T ret) {
        this(rc, ret, null);
    }

    private Result(int rc, T ret, String err) {
        setRc(rc);
        setRet(ret);
        setErr(err);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public int getRc() {
        return rc;
    }

    public void setRc(int rc) {
        this.rc = rc;
    }

    public String getErr() {
        return this.err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public T getRet() {
        return ret;
    }

    public void setRet(T ret) {
        this.ret = ret;
    }
}



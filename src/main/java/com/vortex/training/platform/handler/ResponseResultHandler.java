package com.vortex.training.platform.handler;


import com.alibaba.fastjson.JSONObject;
import com.vortex.training.platform.annotation.ResponseResult;
import com.vortex.training.platform.constants.Constants;
import com.vortex.training.platform.constants.ResultCode;
import com.vortex.training.platform.dto.Result;
import com.vortex.training.platform.exception.ParamErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

/**
 * @author : light
 * @date: 2020/10/30 14:46
 */
@RestControllerAdvice
@Slf4j
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseResult.class;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        Boolean flag = AnnotatedElementUtils.hasAnnotation(methodParameter.getContainingClass(), ANNOTATION_TYPE)
                || methodParameter.hasMethodAnnotation(ANNOTATION_TYPE);
        return flag;
    }

    @Override
    public Object beforeBodyWrite(Object object, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        if (object instanceof Result) {
            return object;
        }
        log.info("response rewrite start.Object = {}", JSONObject.toJSONString(object));
        return Result.newSuccess(ResultCode.SUCCESS.getCode(), object);
    }

    /**
     * 异常统一处理
     * @param e 异常对象
     * @return 返回给前台的结果
     */
    @ExceptionHandler(value = Exception.class)
    public Result<Object> exceptionHandler(Exception e) {
        log.error("异常信息stackTrace：{}，message={}", e.getStackTrace(), e.getMessage());
        if (e instanceof ParamErrorException) {
            return Result.newFailed(ResultCode.PARAM_IS_BLANK.getCode(), e.getMessage());
        }
        return Result.newFailed(ResultCode.SERVER_ERROR.getCode(), ResultCode.SERVER_ERROR.getMessage());
    }
}

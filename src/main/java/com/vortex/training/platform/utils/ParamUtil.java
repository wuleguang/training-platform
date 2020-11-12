package com.vortex.training.platform.utils;


import com.vortex.training.platform.exception.ParamErrorException;
import org.springframework.util.StringUtils;

/**
 * 检验参数的工具类
 *
 * @author Marrisa
 * @since 2019/11/18
 */
public class ParamUtil {

    /**
     * 校验参数是否为空
     * @param paramName paramName
     * @param  paramValue paramValue
     * @throws ParamErrorException ParamErrorException
     */
    public static void isStringParamEmpty(String paramName, String paramValue) throws ParamErrorException {
        if (StringUtils.isEmpty(paramValue)) {
            throw new ParamErrorException(paramName + "不能为空");
        }
    }

    /**
     * 校验参数是否为空
     * @param paramName paramName
     * @param  paramValue paramValue
     * @throws ParamErrorException ParamErrorException
     */
    public static void isObjectParamEmpty(String paramName, Object paramValue) throws ParamErrorException {
        if (paramValue == null) {
            throw new ParamErrorException(paramName + "不能为空");
        }
    }

}

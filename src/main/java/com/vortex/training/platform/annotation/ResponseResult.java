package com.vortex.training.platform.annotation;

import java.lang.annotation.*;

/**
 * @author : light
 * @date: 2020/10/30 16:08
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface ResponseResult {

}

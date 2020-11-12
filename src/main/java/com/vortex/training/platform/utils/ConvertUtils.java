package com.vortex.training.platform.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

import java.util.function.Supplier;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

/**
 * @author : light
 * @date: 2020/10/30 14:22
 */
public class ConvertUtils {
    public static final ModelMapper MODEL_MAPPER = new ModelMapper();

    static {
        Configuration configuration = MODEL_MAPPER.getConfiguration();
        // 解决dto转换成entity时，会把dto里的~Id（dto嵌套对象的id）复制给entity的id的问题
        configuration.setMatchingStrategy(STRICT);
    }

    /**
     * dto转do
     * @param source source
     * @param supplier supplier
     * @param <S> S
     * @param <T> T
     * @return T
     */
    public static <S, T> T modelMap(S source, Supplier<T> supplier) {
        T target = supplier.get();
        MODEL_MAPPER.map(source, target);
        return target;
    }
}

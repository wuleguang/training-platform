package com.vortex.training.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author light
 * @since 2020-10-30
 */
@SpringBootApplication
@MapperScan("com.vortex.training.platform.mapper")
public class PlatformApplication {

    /**
     * 启动类
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(PlatformApplication.class, args);
    }
}

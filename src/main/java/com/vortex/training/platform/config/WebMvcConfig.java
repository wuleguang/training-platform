package com.vortex.training.platform.config;


/**
 * @author : light
 * @date: 2020/10/30 16:47
 */
/*
@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private ResponseResultInterceptor resultInterceptor;

    */
/**
     * 将拦截器注册到spring mvc
     * @param registry registry
     *//*

    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(resultInterceptor);
        registration.addPathPatterns("/**");
        registration.excludePathPatterns("/doc.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
*/

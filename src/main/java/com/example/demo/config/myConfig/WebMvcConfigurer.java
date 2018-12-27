package com.example.demo.config.myConfig;

import com.example.demo.config.MyIntercept;
import com.example.demo.converter.DateConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyIntercept()).addPathPatterns("/**")
                 .excludePathPatterns("/myUser/getLogin")
                 .excludePathPatterns("/myUser/getRegister")
                 .excludePathPatterns("/myUser/login")
                 .excludePathPatterns("/myUser/register")
                 .excludePathPatterns("/images/**")
                 .excludePathPatterns("/js/**")
                 .excludePathPatterns("/**/validate*")
                 .excludePathPatterns("/myPassword/preFindPassword") ;

        super.addInterceptors(registry);
    }
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    @Override
    protected void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter());
        super.addFormatters(registry);
    }
}

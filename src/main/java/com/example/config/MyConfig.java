package com.example.config;

import com.example.entity.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 配置类
 * 1.配置类里面使用@Bean标注在方法上给容器注册组件,默认也是单例的
 * 2.配置类本身也是组件
 * 3.proxyBeanMethods: 代理bean的方法
 *   full(proxyBeanMethods = true)
 *   lite(proxyBeanMethods = false)
 *   组件依赖
 * @Author: chenchong
 * @Date: 2021/10/29 18:37
 */
//@Configuration(proxyBeanMethods = true)
@Configuration(proxyBeanMethods = false)
public class MyConfig {
    @Bean
    public Student chenchong(){
        return new Student("chenchong",23);
    }
}

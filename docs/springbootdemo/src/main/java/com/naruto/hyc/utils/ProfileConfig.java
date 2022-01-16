package com.naruto.hyc.utils;

import com.naruto.hyc.bean.School;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Configuration;

//声明本类是一个配置类
@Configuration
public class ProfileConfig {


    //这是一个Bean对象
    @Bean
    //profile为pro时实例化prDemoBean
    public School proDemoBean() {
        return new School();
    }
}
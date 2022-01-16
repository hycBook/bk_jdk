package com.naruto.hyc.springbootdemo;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Applaction {
    @Autowired
    private Environment env;

    @RequestMapping("/env")
    public Map<String, Object> env() throws UnsupportedEncodingException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", env.getProperty("school.username"));
        return map;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(Applaction.class);
        application.run(args);
    }

}

package com.naruto.hyc.controller;

import com.naruto.hyc.bean.School;
import com.naruto.hyc.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class HelloController {
    @Autowired
    Student student;

    @Autowired
    School school;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World! " + student.getName() + " " + school.getUsername();
    }
}
package com.example.serviceverifycode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ServiceVerifyCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceVerifyCodeApplication.class, args);
    }
    @GetMapping("/test")
    public String test(){
        return "短信验证服务";
    }
}

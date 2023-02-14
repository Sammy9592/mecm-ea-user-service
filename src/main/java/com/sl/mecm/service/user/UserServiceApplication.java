package com.sl.mecm.service.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

import lombok.extern.slf4j.Slf4j;


@SpringBootApplication(scanBasePackages = "com.sl.mecm.*")
@EnableAutoConfiguration
@EnableWebFlux
@Slf4j
public class UserServiceApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(UserServiceApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

package com.giannis.blogApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class BlogApiApplication {
    public static void main(String[] args){
        SpringApplication.run(BlogApiApplication.class,args);
    }
}

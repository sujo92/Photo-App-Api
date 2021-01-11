package com.example.photoapp_api_users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.beans.BeanProperty;

@SpringBootApplication
@EnableDiscoveryClient
public class PhotoappApiUsersApplication {

    public static void main(String[] args) {

        SpringApplication.run(PhotoappApiUsersApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

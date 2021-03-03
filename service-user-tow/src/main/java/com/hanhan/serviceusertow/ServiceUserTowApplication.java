package com.hanhan.serviceusertow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class ServiceUserTowApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceUserTowApplication.class, args);
    }

}

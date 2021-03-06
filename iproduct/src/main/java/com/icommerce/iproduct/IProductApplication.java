package com.icommerce.iproduct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.LocalDateTime;

@Slf4j
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class IProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(IProductApplication.class, args);
        log.info("Server started at: " + LocalDateTime.now());
        String _2 ="ssss";
        System.out.println(_2);
    }
}

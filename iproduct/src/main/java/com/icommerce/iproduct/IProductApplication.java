package com.icommerce.iproduct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@Slf4j
@SpringBootApplication
//@EnableEurekaClient
//@EnableFeignClients
public class IProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(IProductApplication.class, args);
        log.info("Server started at: " + LocalDateTime.now());
    }
}

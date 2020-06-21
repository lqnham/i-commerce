package com.icommerce.iaudit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.time.LocalDateTime;

@Slf4j
@SpringBootApplication
//@EnableEurekaClient
public class IAuditApplication {

    public static void main(String[] args) {
        SpringApplication.run(IAuditApplication.class, args);
        log.info("Server started at: " + LocalDateTime.now());
    }

}

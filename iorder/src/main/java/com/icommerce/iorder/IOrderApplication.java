package com.icommerce.iorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class IOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(IOrderApplication.class, args);
    }

}

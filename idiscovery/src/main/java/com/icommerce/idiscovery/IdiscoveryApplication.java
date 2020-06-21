package com.icommerce.idiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class IdiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdiscoveryApplication.class, args);
    }

}

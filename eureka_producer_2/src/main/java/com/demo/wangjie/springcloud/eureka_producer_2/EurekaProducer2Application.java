package com.demo.wangjie.springcloud.eureka_producer_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EurekaProducer2Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaProducer2Application.class, args);
    }

}

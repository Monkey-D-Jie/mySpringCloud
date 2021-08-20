package com.demo.wangjie.springcloud.eureka_consumer_hystrix_dashboard_node01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@SpringBootApplication
public class EurekaConsumerHystrixDashboardNode01Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerHystrixDashboardNode01Application.class, args);
    }

}

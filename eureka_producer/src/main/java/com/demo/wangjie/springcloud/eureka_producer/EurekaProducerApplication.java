package com.demo.wangjie.springcloud.eureka_producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//允许被客户端发现的注解标识->
// 打上该注解后，项目就有了服务注册的功能。然后就能到服务注册中心注册了
@EnableDiscoveryClient
public class EurekaProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaProducerApplication.class, args);
    }

}

package com.micro.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: micro
 * @description: 网关启动类
 * @author: XiongJiaMin
 * @create: 2022-01-04 16:05
 **/
@SpringBootApplication(scanBasePackages = {"com.micro.gateway"})
@EnableDiscoveryClient
public class GatewayApp {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApp.class, args);
    }
}

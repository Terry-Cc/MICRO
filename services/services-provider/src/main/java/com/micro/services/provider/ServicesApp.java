package com.micro.services.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: micro
 * @description: 启动类
 * @author: XiongJiaMin
 * @create: 2021-12-02 17:47
 **/
@SpringBootApplication(scanBasePackages = {"com.micro.common", "com.micro.services.provider"})
@EnableDiscoveryClient
public class ServicesApp {

    public static void main(String[] args) {
        SpringApplication.run(ServicesApp.class, args);
    }
}

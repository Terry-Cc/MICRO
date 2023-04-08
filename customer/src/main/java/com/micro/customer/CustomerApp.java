package com.micro.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: micro
 * @description: 消费启动类
 * @author: XiongJiaMin
 * @create: 2021-12-16 17:32
 **/
@SpringBootApplication(scanBasePackages = {"com.micro.common", "com.micro.customer"})
@EnableDiscoveryClient
public class CustomerApp {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApp.class, args);
    }
}

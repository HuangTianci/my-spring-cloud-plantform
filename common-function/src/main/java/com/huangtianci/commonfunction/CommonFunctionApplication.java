package com.huangtianci.commonfunction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Huang Tianci
 * @date 2017/11/22
 */
@SpringBootApplication
@EnableDiscoveryClient
@ImportResource("classpath:spring/*.xml")
@ComponentScan
public class CommonFunctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonFunctionApplication.class, args);
    }
}

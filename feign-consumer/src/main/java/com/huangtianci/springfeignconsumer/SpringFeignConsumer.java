package com.huangtianci.springfeignconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author Huang Tianci
 * @date 2017/12/5
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class SpringFeignConsumer {

    public static void main(String[] args) {
        SpringApplication.run(SpringFeignConsumer.class, args);
    }
}

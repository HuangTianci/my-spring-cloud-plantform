package com.huangtianci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author Huang Tianci
 * @date 2017/12/4
 */
@SpringBootApplication
@EnableDiscoveryClient
public class RibbonConsumerApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(RibbonConsumerApp.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

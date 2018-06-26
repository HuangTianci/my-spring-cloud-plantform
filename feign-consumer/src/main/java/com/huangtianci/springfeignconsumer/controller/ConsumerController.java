package com.huangtianci.springfeignconsumer.controller;

import com.huangtianci.springfeignconsumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Huang Tianci
 * @date 2017/12/5
 */
@RestController
public class ConsumerController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/feign-consumer")
    public String helloConsumer() {
        System.out.println("开始调用spring-common-function的hello接口");
        return helloService.hello();
    }
}

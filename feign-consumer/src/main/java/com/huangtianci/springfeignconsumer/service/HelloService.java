package com.huangtianci.springfeignconsumer.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Huang Tianci
 * @date 2017/12/5
 */
@FeignClient("common-function")
public interface HelloService {

    @GetMapping("/hello")
    String hello();

}

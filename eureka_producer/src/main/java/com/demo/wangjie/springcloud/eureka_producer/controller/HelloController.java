package com.demo.wangjie.springcloud.eureka_producer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2021-08-12 11:02
 * @Description: 服务提供者
 * To change this template use File | Settings | File and Templates.
 */
@RestController
public class HelloController {
    //参见疑问点1
    @RequestMapping(value = "/hello")
    public String index(@RequestParam String name){
         return "hello "+name+"，this is producer message";
    }
}

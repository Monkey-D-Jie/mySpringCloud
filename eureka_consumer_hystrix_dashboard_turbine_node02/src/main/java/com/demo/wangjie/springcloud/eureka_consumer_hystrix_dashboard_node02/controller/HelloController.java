package com.demo.wangjie.springcloud.eureka_consumer_hystrix_dashboard_node02.controller;

import com.demo.wangjie.springcloud.eureka_consumer_hystrix_dashboard_node02.remote.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2021-08-12 14:00
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */
@RestController
public class HelloController {

    @Autowired
    private HelloRemote helloRemote;

    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
        return helloRemote.hello2(name);
    }
}

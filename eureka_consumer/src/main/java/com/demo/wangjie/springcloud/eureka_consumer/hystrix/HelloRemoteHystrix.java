package com.demo.wangjie.springcloud.eureka_consumer.hystrix;

import com.demo.wangjie.springcloud.eureka_consumer.remote.HelloRemote;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2021-08-13 14:02
 * @Description: 模拟熔断的类
 * To change this template use File | Settings | File and Templates.
 */
@Component
public class HelloRemoteHystrix implements HelloRemote {


    @Override
    public String hello(@RequestParam(value = "name") String name) {
        return "hello " +name+", this message send failed -From Hystrix";
    }
}

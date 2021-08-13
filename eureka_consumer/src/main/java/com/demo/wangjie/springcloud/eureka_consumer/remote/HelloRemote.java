package com.demo.wangjie.springcloud.eureka_consumer.remote;

import com.demo.wangjie.springcloud.eureka_consumer.hystrix.HelloRemoteHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2021-08-12 13:53
 * @Description: Feign调用实现
 * To change this template use File | Settings | File and Templates.
 */
/*
1.@FeignClient(name = "spring-cloud-producer", fallback = HelloRemoteHystrix.class)

 * 这里的name即指代的远程服务名。
 * 因为是要调用服务提供者的服务，所以在远程服务名称上要同远程服务保持一致。
 * 相应的，下方接口指定的方法也就需要同服务中的方法命名保持一致了。
 * 这里就相当于在Client中直接把producer中的服务引入了进来，
 * 但Client这边只管调用，具体逻辑实现则交由服务端去实现。

2.@Primary

这个注解的作用时标识当项目中出现了多个A接口的实现类，且都交由Spring管理的时候，哪个作为主类。
 * */


@FeignClient(name = "spring-cloud-producer", fallback = HelloRemoteHystrix.class)
@Primary
public interface HelloRemote {

    @RequestMapping(value = "/hello")
    public String hello(@RequestParam(value = "name") String name);
}

# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.9/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.9/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides

###1.demo原连接地址

http://www.ityouknow.com/springcloud/2017/05/12/eureka-provider-constomer.html

###2.疑问1 ：Springboot中@RequestMapping注解默认是支持什么类型的请求呢？Post or Get？

答：默认情况下，如果不写的话，Post和Get方法它都是支持的，会自动地根据前端的请求来做适配。
如果要写的话，可以参照下面的方式来写。
``@RequestMapping(value = "/hello",method={RequestMethod.POST,RequestMethod.GET(两个方法中选择一个)})``

* [RequestMapping指定方法解析](https://blog.csdn.net/weixin_44137201/article/details/108537745)

###疑问3：为什么在启动类上打了注解@EnableDiscoveryClient,项目成功启动后，依然没有注册上呢？

答：笔者这里就遇到了这种情况。在启动类上打了@EnableDiscoveryClient注解后，项目能正常启动。但就是不能注册到Eureka的注册中心上去。
一番搜罗后，碰巧在这里找到了解决方案。
[Eureka客户端启动失败！@EnableEurekaClient注解无效？客户端注册失败？](https://blog.csdn.net/l_z_w99/article/details/95360752)

把下面的这段pom依赖加上后，就能注册上了。原因未知。。。
`<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
            <version>3.0.0</version>
        </dependency>
`




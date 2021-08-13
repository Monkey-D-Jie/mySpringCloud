
* [demo链接原地址](http://www.ityouknow.com/springcloud/2017/05/12/eureka-provider-constomer.html)



## 问题记录
###1 使用注解@EnableFeignClients无效
   按着demo中的pom内容，引入依赖。发现不能导入上述的注解。
   后来在下面的帖子里找到了解决办法。
   
   [Springboot2.x中引入@EnableFeignClients注解的正确姿势](https://www.cnblogs.com/sxdcgaq8080/p/9860939.html)
   
   关于其中的缘由，还是参见链接[Spring Boot 2下使用Feign找不到@EnableFeignClients的解决办法](https://blog.csdn.net/alinyua/article/details/80070890)
    
   简单来说，就是因为jar包依赖升级，跟这个注解相关的jar包被迁移到了另一个依赖下。只有导入正确的那个才能正常的使用这个注解。

###2。项目启动报错-Fangframework.cloud.ciled to introspect Class [org.spriontext.properties.ConfigurationPropertiesBeans] from ClassLoader [sun.misc.Launcher$AppClassLoader@18b4aac2]
   从字面的意思来看：Failed to introspect class 未能从类集合里找到指定的类。
   
   参考链接：[异常解决：java.lang.IllegalStateException: Failed to introspect Class](https://www.cnblogs.com/jpfss/p/11089083.html#_10)


   在上面这个帖子里，并没有找到解决方案。找了下项目中集成进来的两个jar包 spring-cloud-context 2.2.5.RELEASE和3.0.0的[org.springframework.cloud.context]
目录，都没有properties这个目录。难不成是包没找对？
   
   有两个不同版本的jar包-会不会是jar包冲突？我找了，并没有所谓的冲突在。

   然后再看了下报错的堆栈信息，发现了这个异常：**Caused by: java.lang.ClassNotFoundException: org.springframework.boot.context.properties.ConfigurationBeanFactoryMetadata**
   
   这种类找不到的异常，大概率是因为缺少了依赖。

   然后又重找解决方案。在下面这个帖子里找到了些线索：因为Springboot和SpringCloud的版本不同导致的问题。
   
   [springboot项目启动报错找不到ConfigurationPropertiesBean？](https://cloud.tencent.com/developer/ask/230286)
   
   笔者看了下自己的demo，发现producer用的**EnableDiscoveryClient**注解引用的spring-cloud-context-3.x的包。但consumer中用的Feign
用的则是2.2.5版本的。根据版本对应关系，把后者的版本调成了3.0.1,果然就没有报ClassNotFoundException这个错误了。
   
   跟着开心地启动程序，却报了一个：未能找到 loadBanlancing 负载均衡服务器的错误。再到Eureka注册中心切瞅了瞅，发现producer并没有注册到注册中心中去。

   经过一番调整，已经成功地把producer注册到注册中了。现在再启动看看是个怎样的效果。
   
   重新启动后，又出现了**No Feign Client for loadBalancing defined. Did you forget to include spring-cloud-starter-loadbalancer**的错误。

   在网上搜罗一番后，在这里找到了良方[No Feign Client for loadBalancing defined. Did you forget to include spring-cloud-starter-loadbalancer?
-发生了什么？？？](https://blog.csdn.net/weixin_43556636/article/details/110653989)
   
   原来是因为**由于SpringCloud Feign在Hoxton.M2 RELEASED版本之后不再使用Ribbon而是使用spring-cloud-loadbalancer，所以不引入spring-cloud-loadbalancer会报错**
   
   在原来的基础上，加了下面这段依赖，然后再启动，就OK了。
   `<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-loadbalancer</artifactId>
            <version>3.0.3</version>
        </dependency>
   `

* 2021年8月13日13:07:43 更新
 接下来，按照帖子继续往下做起走。
 [springcloud(四)：熔断器Hystrix - 纯洁的微笑博客](http://www.ityouknow.com/springcloud/2017/05/16/springcloud-hystrix.html) 
  


###3-熔断器Hystrix
**不同的服务就跟一条生产线上的组件似的，
服务出问题的时候，最终会体现在连贯着的生产线上。所以，通常我们说的熔断都是服务服务调用端而言的**

####3.1-雪崩效应和熔断器
* 雪崩效应

在微服务架构中，为了方便逻辑上的流式处理。所有服务都是跟多米诺骨牌似的关联起来的。
  
**服务传递A--->B--->C--->D--->E**

一旦其中的A服务出了问题，随着时间的推移。后续的其他服务也会受到影响。最终一个小的问题，就会跟滚雪球一样，越滚越大。
成为一个雪崩级的问题。整一片都会垮下来。

* 熔断器

它跟我们日常理解的电表里的保险丝有点类似。电表里的保险丝，在出现短路的时候，会因为电流异常过大，而即使断开，然电流回路失效，
从而保证人们的生命财产安全。

在微服务里的熔断器扮演的就是一个“保险丝”的角色。当应用服务的错误率达到了设定的阈值时，就会触发熔断。这样的好处是能降低雪崩问题
发生的可能性。同时，也能有效地减少外部请求对服务器带来的“无意义消耗”——就像破壁机是拿来碎东西的，一个劲儿地让它在那儿空转，
就会造成浪费了嘛。但熔断器本身是会根据应用程序的恢复情况来对“熔断选择”做出调整的。如果应用程序能自行恢复，相应的，熔断器也会
自动关闭掉。

_熔断器就是服务高可用的最后一道防线_

####3.2-在Hystrix特性
* 断路器机制

当外部请求访问后端服务超过默认的50%比例后，断路器就会变为开路状态。后面来的请求，都会被直接失败而不会流转到后端服务部分。
这种状态在持续了默认的5s之后，断路器又会变成半开路状态，然后检测当前请求的成功率如何。如果成功，则断路器会切回闭路状态。反之，
则又会切回到开路状态。断路器闭路和开路状态的切换，可以有效地避免无效的请求来占用后端服务的带宽消耗。同时，它还具有自行检测服务状态
，并根据实际情况恢复的能力。

* Fallback 

它相当于是一个降级操作。在后端服务出现问题的时候，可以直接由其返回预设的错误提示信息，而不必要具体的服务中去溜一圈再返回。

* 资源隔离

在Hystrix中，主要是通过线程池来实现资源的隔离。在服务比较多的情况下，如果把它们都放在一块儿，其中一个出了问题，可能就会影响到
其他的服务。所以通常会根据不同的业务处理形式将调用服务的Command放到不同的线程池中，减少相互间的影响。资源是相对的——这样做了后，
维护这些线程池就需要额外的开销去做。

_如果是对性能有严格要求而且确信自己调用服务的客户端代码不会出问题的话, 可以使用Hystrix的信号模式(Semaphores)来隔离资源_

###### ???Hystrix的信号模式是个啥？-待查阅

####集成Hystrix熔断器的问题汇总
* 问题1：在加入了HelloRemoteHystrix类后，HellRemote注入报错问题

  笔者在加入了HelloRemoteHystrix类后，原来的HelloRemote注入出现了红色的波浪线错误。

  这是因为在项目中的HelloRemoteHystrix即实现了HelloRemote接口类实现的。但它也被Spring作为是HelloRemote的
一个实例类来处理了。这种报错相当于是一个warning。不会影响项目的正常启动。
  
  解决方案：[解决IDEA报错Could not autowire. There is more than one bean of 'xxx' type](https://www.cnblogs.com/rever/p/11250396.html)

  关键内容摘录

    
    `1.给不同的实现标注名字
    使用Qulifier注解标注
  
    @Autowired
    @Qualifier(name = 'testService1')
    private TestService testService;
    
    2.使用@Primary
    @Component
    @Primary
    public class TestService{}
    `
* 问题2：feign.hystrix.enabled=true断路器设置设置未生效
    
  关闭producer后，再访问同样的接口，出现了Whiteble Error Page的问题。后来在下面这篇帖子里找到了正确的处办法。

  解病良方--->>>[Spring Cloud 2020: Hystrix不生效怪我咯-技术圈](https://jishuin.proginn.com/p/763bfbd4ec5b)

  笔者按照帖子中的做法，调整了自己的demo

  第一步：将hystrix的配置更改为：feign.circuitbreaker.enabled=true。重启服务后，发现并未生效，依然是报了Whiteable
Error Page 的错误。
  
  然后再试了第二步：查看Circuitbreaker的接口类，发现确实没有相关的实现类。然后按照帖子里的做法，在pom文件中引入了依赖。再重启服务
，就能成功地在停掉Producer服务后，原请求得到的返回结果为HelloRemoteHystrix中自定义的错误信息。
  
  至此，问题解决，Hystrix的demo模拟成功了。

  这里，必须要大赞一番良方中作者的话：解决问题的思路更重要。回到这个问题上来就是，
  
    _1.在网上找的帖子，有些用的依赖版本比较老； 

    2.我们在自己的demo里用的，可能是比较新的版本，配置项可能会发生一些变化；
  
    3.配置项没生效的话，可以怎么去看排查呢？
  
    ①：看下自己用依赖版本是否跟学习帖中的版本一致？
  
    ②：如果不一致，则需要辅以网上能搜到的资料到配置项相关的源码中去看，定位 新的配置项 方法和名称；
  
    ③：改了之后，如果还是没有生效的，则还需要到新配置项下相关的源码中去找原因，看看是
  
    版本低了还是依赖错了~~~
    _

 







   



   
   
   
   
   
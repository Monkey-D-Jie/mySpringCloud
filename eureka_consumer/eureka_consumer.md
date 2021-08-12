
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
   

   



   
   
   
   
   
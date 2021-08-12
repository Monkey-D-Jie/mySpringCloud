# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.3/maven-plugin/reference/html/#build-image)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

2021年7月26日14:11:15 更新

1.demo来源链接：http://www.ityouknow.com/springcloud/2017/05/10/springcloud-eureka.html

2.双节点注册的时候，需要修改本地的hosts文件。用来映射自定义的地址。

**hosts文件所在目录：C:\Windows\System32\drivers\etc**

3.编译打包时出现了错误
**org.apache.maven.plugins:maven-resources-plugin:3.2.0:resources (default-resources) on project spring_cloud_eureka:
Input length = 1 -> [Help 1]**

原因：properties配置文件的编码类型不对。出现这个错误，很可能是配置文件为非UTF-8编码类型的。

解决方法：将配置文件的编码格式改为UTF-8即可。更多方法可以参见对应链接。 [length=-1 spring启动报错](https://blog.csdn.net/u013202238/article/details/110915761)

4.springboot打包时跳过单元测试目录的方法：在**properties**标签下新增配置项**skipTests-true**即可。就像下面这样。

`<properties>
<java.version>1.8</java.version>
<spring-cloud.version>2020.0.0</spring-cloud.version>
<!-- 打包时，跳过单元测试test目录-->
<skipTests>true</skipTests>
</properties>`

5.Eureka自我保护机制下的问题 简单来说，就是Eureka考虑到实际应用场景中的网络延迟问题。默认对下线的服务做了一个“自我保护”——它会判断注册的服务同自己的通信
在一段时间内是否正常。当达到一定的丢失率后，才会断定注册的服务已经下线。为了更好的测试，直接在demo中关掉了Eureka的自我保护机制。 更多解释可以参加下方链接。

[服务已下线。但可用服务列表中仍能看见？是什么情况](https://www.cnblogs.com/gudi/p/8645370.html)



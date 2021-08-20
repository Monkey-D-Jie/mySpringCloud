# Getting Started

### demo调测全纪实


* [demo原贴](http://www.ityouknow.com/springcloud/2017/05/18/hystrix-dashboard-turbine.html)

_2021年8月17日17:11:17 更新_

### 问题1-启动项目报错

按照demo中的做法，把maven依赖加上后，启动项目，报错了。

报的错误是：Caused by: java.lang.NoSuchMethodError: javax.servlet.ServletContext.getVirtualServerName()Ljava/lang/String;

感觉是没找到某一个类。

考虑1：jar包冲突

笔者多模块的项目出现了很多雷同，但版本不一样的jar包。不知道是否跟这个有关了。

看了冲突的，还不少。故暂时先不考虑从这个角度出发。

最后在这篇帖子里找到了解决的方法。

* 帖子1（笔者就是在这个帖子里找到解决思路的）：[解决javax.servlet.ServletContext.getVirtualServerName()Ljava/lang/String](https://blog.csdn.net/LLittleF/article/details/104197930)

* 帖子2[SpringBoot报错：java.lang.NoSuchMethodError: javax.servlet.ServletContext.getVirtualServerName()Ljava/lang/String](https://www.cnblogs.com/lvbinbin2yujie/p/10726122.html)

考虑2：少了依赖

那就从这块儿入手。在网上找了下，更多的帖子都指向了jar包冲突这个方向。

_2021年8月18日16:57:47 更新_

### 问题2-进入monintor后报错[Unable to connect to Command Metric Stream]

不晓得这是什么原因造成的。只有先搜为敬了。

在一番搜罗后，找到了解决办法。需要注意的是，要测试这个demo的话，需要按照顺序依次把spring_cloud_eureka,eureka_producer,

eureka_consumer_hystrix_dashboard3个项目启动起来。然后再到页面上输入帖子中提到的地址，查看demo的实际情况。



帖子1：
(41条消息) Unable to connect to Command Metric Stream. 问题解决_大痴小乙的博客-CSDN博客
https://blog.csdn.net/fxbin123/article/details/82322476

帖子2：
(41条消息) springboot2.0下hystrix dashboard Unable to connect to Command Metric Stream解决办法_ddxd0406的博客-CSDN博客
https://blog.csdn.net/ddxd0406/article/details/79643059?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.control

帖子3：就是在这篇帖子里找到的解决方案
(41条消息) 问题记录：Hystrix Dashboard 提示：Unable to connect to Command Metric Stream._Coufran的博客-CSDN博客
https://blog.csdn.net/Coufran/article/details/108107952



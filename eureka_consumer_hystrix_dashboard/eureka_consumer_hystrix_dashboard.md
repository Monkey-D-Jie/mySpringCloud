# Getting Started

### demo调测全纪实


* [demo原贴](http://www.ityouknow.com/springcloud/2017/05/18/hystrix-dashboard-turbine.html)

_2021年8月17日17:11:17 更新_

### 1-启动项目报错

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

很多朋友在WINDOWS下会用批处理去启动自己的java程序，
一般的写法是
运行class：
java xx

运行jar：
java -jar xxx.jar
但是这样运行会有一个恶心的对话框停在那直到我们关闭程序。


于是很多人说可以这样
运行class：
start javaw xx
运行jar：
start javaw -jar xxx.jar  

这种方法DOS窗口还是会一闪而过，这就算解决问题了吗？！网上很多人说是的.
对我们这种追求完美的人来说闪一下还是不能接受滴.

于是终极解决方案出现了！
那就是在批处理第一行加上@echo off

这样我们的批处理就变成了

运行class：
@echo off
start javaw xx

运行jar：
@echo off
start javaw -jar xxx.jar  


快试试吧，绝对不闪了。哈哈哈。

解释一下
 echo off
表示在此语句后所有运行的命令都不显示命令行本身 
@ 表示运行时不显示本命令行
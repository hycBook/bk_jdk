![异世界.png](https://upload-images.jianshu.io/upload_images/15675864-e39212ac990782cf.png)

[TOC]

# spark环境搭建

## scala

> 下载并安装scala

* 下载 [scala-2.12](https://www.scala-lang.org/download/2.12.15.html)
* 安装`rpm -ivh scala-2.12.15.rpm`
* 查看scala版本`scala -version`

> 配置环境

* 编辑`/etc/profile`，文件末尾添加配置

  ```sh
  export SCALA_HOME=/usr/local/scala/scala-2.11.8
  export PATH=PATH:SCALA_HOME/bin
  ```

  

## hadoop

> 下载并安装scala

* 下载 [hadoop-3.2.0](https://www.apache.org/dyn/closer.cgi/hadoop/common/hadoop-3.2.0/hadoop-3.2.0.tar.gz)
* 安装


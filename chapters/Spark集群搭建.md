![img](res/other/异世界蕾姆_1.png)

[TOC]

# spark环境搭建

## 版本总览

```sh
(ray37) [root@Slave03 huangyc]# java -version
java version "1.8.0_281"

(ray37) [root@Slave03 huangyc]# hadoop version
Hadoop 3.2.1
Compiled with protoc 2.5.0

(ray37) [root@Slave03 huangyc]# scala -version
Scala code runner version 2.12.15 -- Copyright 2002-2021, LAMP/EPFL and Lightbend, Inc.

(ray37) [root@Slave03 huangyc]# sh /usr/spark-3.0/bin/spark-shell
Spark context Web UI available at http://Slave03:4040
Welcome to
      ____              __
     / __/__  ___ _____/ /__
    _\ \/ _ \/ _ `/ __/  '_/
   /___/ .__/\_,_/_/ /_/\_\   version 3.0.3
      /_/  
Using Scala version 2.12.10 (Java HotSpot(TM) 64-Bit Server VM, Java 1.8.0_281)
```



## scala

> 下载并安装scala

* 下载 [scala-2.12](https://www.scala-lang.org/download/2.12.15.html)
* 安装`rpm -ivh scala-2.12.15.rpm`
* 查看scala版本`scala -version`

> 配置环境

* 查询包中**文件安装位置** -l：
  - `rpm -ql 包名`

* 编辑`/etc/profile`，文件末尾添加配置

  ```sh
  # 配置scala环境变量
  export SCALA_HOME=/usr/share/scala/bin
  export PATH=$PATH:$SCALA_HOME
  ```
  

执行`source /etc/profile`令其生效

> 其他节点同样配置即可



## 免密登录

### 修改主机名(非必须)

> 查看主机名用`hostname`
>
> 修改主机名`/etc/hostname`
>
> 修改后重启服务器`reboot`

> 配置host文件`vi /etc/hosts`

```sh
192.168.123.21  Slave00
192.168.123.23  Slave02
192.168.123.24  Slave03
```

> `/etc/init.d/network restart`

### 生成秘钥对

> 使用 `RSA` 类型的加密类型来创建密钥对

```sh
# 生成秘钥(-f换名字好像不行)
ssh-keygen -t rsa
# 配置本机免密
cat id_rsa.pub>>authorized_keys
```

* -f 参数表示指定密钥对生成位置与名称 
* 密钥对通常放在 `~/.ssh`目录下 
* 回车即可创建密钥对，需要输入密码如果不需要为密钥对进行加密，那么可以一路回车
* 有需要清空文件的可以执行`truncate -s 0 authorized_keys`

创建成功之后，可以看到 .ssh 目录下多了两个文件，分别是：

1. id_key：密钥对的**私钥**，通常放在**客户端**
2. id_rsa.pub：密钥对中的**公钥**，通常放在**服务端**

### 秘钥上传服务器

> 文件权限

```sh
sudo chmod -R 700 ~/.ssh
sudo chmod -R 600 ~/.ssh/authorized_keys
```

> 将your_key.pub 公钥文件上传至需要连接的服务器

```sh
# 方式一：追加在其他服务器文件末尾(本机不需要)
cat ~/.ssh/id_rsa.pub | ssh root@192.168.123.21 "cat - >> ~/.ssh/authorized_keys"
cat ~/.ssh/id_rsa.pub | ssh root@192.168.123.23 "cat - >> ~/.ssh/authorized_keys"
cat ~/.ssh/id_rsa.pub | ssh root@192.168.123.24 "cat - >> ~/.ssh/authorized_keys"

# 方式二
ssh-copy-id -i ~/.ssh/id_key.pub root@192.168.123.21
ssh-copy-id -i ~/.ssh/id_key.pub root@192.168.123.23
ssh-copy-id -i ~/.ssh/id_key.pub root@192.168.123.24
```

* -i 参数表示使用指定的密钥，-p参数表示指定端口，ssh 的默认端口是 22

### 公钥查看

本地的公钥文件上传在服务器的.ssh/authorized_keys 文件中

```sh
cat ~/.ssh/authorized_keys
```

### 免密检测

```sh
ssh Slaver00
ssh Slaver02
ssh Slaver03
```



## hadoop

### 新建用户(非必须)

> 新建hadoop用户

```sh
useradd -m hadoop -s /bin/bash
```

> 修改hadoop用户密码

```sh
passwd hadoop
```

> 切换用户

```sh
su hadoop
```

> 新建文件夹

```sh
mkdir /home/hadoop/apps
mkdir /home/hadoop/data
```

> 关闭防火墙

```sh
systemctl stop firewalld
systemctl disable firewalld
```

> 关闭selinux

```sh
vim /etc/sysconfig/selinux
修改SELINUX=enforcing为SELINUX=disabled
```



### 安装

> 下载并解压hadoop到`/usr/hadoop-3.2.0`

* 下载 [hadoop-3.2.0](https://www.apache.org/dyn/closer.cgi/hadoop/common/hadoop-3.2.0/hadoop-3.2.0.tar.gz)，解压到`/usr/hadoop-3.2.0`

* 配置环境变量

  ```sh
  export HADOOP_HOME=/usr/hadoop-3.2.0
  export PATH=$PATH:$HADOOP_HOME
  ```

* 执行`source /etc/profile`令其生效

* 查看hadoop版本，`hadoop version`

### 配置文件

> Hadoop配置文件的配置，需要配置的文件有几个分别是
>
> * hadoop-env.sh
> * core-site.xml
> * hdfs-site.xml
> * mapred-site.xml
> * yarn-site.xml
> * workers文件
>
> 这些文件均可以在`/usr/hadoop-3.2.0/etc/hadoop`下找到

---

> workers文件

```sh
Slave00
Slave02
Slave03  # 主节点
```

旧版本以及网上的教程是修改`etc/hadoop/slaves`文件，但是新版已经移除了这一个文件，取而代之的是`workers`文件，上述设置代表我的集群有三个`datanode`结点

> hadoop_env.sh

```sh
export JAVA_HOME=${JAVA_HOME}               #设置JAVA_HOME的路径，需要再次指明
export HADOOP_HOME=${HADOOP_HOME}
```

注意的是，如果之前没有设置JAVA_HOME的环境变量，此处直接这样引用会出现错误，改用绝对路径即可消除错误。

> core-site.xml

```xml
<!-- Put site-specific property overrides in this file. -->
<configuration>
    <!-- 设置了主节点的ip -->
    <property>
      <name>fs.defaultFS</name>
      <value>hdfs://Slave03:9000</value>
    </property>
    <!-- 设置了临时目录的地址 -->
    <property>
        <name>hadoop.tmp.dir</name>
        <value>/home/huangyc/hadoop_data/data/tmp</value>
    </property>
</configuration>
```

> hdfs-site.xml

```xml
<!-- Put site-specific property overrides in this file. -->
<configuration>
    <property>
        <name>dfs.namenode.secondary.http-address</name>
        <value>Slave03:50090</value>
    </property>
    <property>
        <!-- 配置网页端口访问 不配置的话 默认端口为9870-->
        <name>dfs.namenode.http.address</name>
        <value>Slave03:50070</value>
    </property>
    <property>
        <name>dfs.replication</name>
        <value>2</value>
    </property>
    <property>
        <name>dfs.namenode.name.dir</name>
        <value>file:///data/hadoop/hdfs/nn</value>
    </property>
    <property>
        <name>dfs.datanode.data.dir</name>
        <value>file:///data/hadoop/hdfs/dn</value>
    </property>
</configuration>
```

> mapred-site.xml

```xml
<!-- Put site-specific property overrides in this file. -->
<configuration>
    <property>
        <name>mapreduce.framework.name</name>
        <value>yarn</value>
    </property>
    <property>
        <name>mapreduce.jobhistory.address</name>
        <value>Slave03:10020</value>
    </property>
    <property>
        <name>mapreduce.jobhistory.webapp.address</name>
        <value>Slave03:19888</value>
    </property>
</configuration>
```

> yarn-site.xml

```xml
<configuration>
  <!-- Site specific YARN configuration properties -->
  <!-- 指定ResourceManager的地址-->
  <property>
      <name>yarn.resourcemanager.hostname</name>
      <value>Slave03</value>
  </property>
  <!-- 指定reducer获取数据的方式-->
  <property>
      <name>yarn.nodemanager.aux-services</name>
      <value>mapreduce_shuffle</value>
  </property>
  <property>
      <name>yarn.nodemanager.local-dirs</name>
      <value>file:///data/hadoop/yarn/nm</value>
  </property>
</configuration>
```



### 从节点设置

> 将hadoop目录拷贝到从节点的对应目录
> 仿照主节点对应的数据目录设置，设置对应的数据目录（即/data一系列目录）

### hadoop启动和测试

> 第一次开启时首先要初始化hdfs

```text
cd /usr/hadoop-3.2.0
./bin/hdfs namenode -format
```

此处需要注意一个问题，第二次开启不需要再次初始化，遇到问题需要再次初始化，建议删除存放文件
上述配置文件中指明了存放数据的文件为dfs，到时候删除即可

> 主节点启动

```sh
cd /usr/hadoop-3.2.0/sbin
# 启动前
./start-all.sh
```

> 查看进程

```sh
# 主节点
[root@Slave03 sbin]# jps
28260 SecondaryNameNode
28810 ResourceManager
27706 DataNode
30236 Jps
29341 NodeManager
27326 NameNode

# 从节点
[root@Slave00 sbin]# jps
3570 DataNode
4115 NodeManager
22413 Worker
27503 Jps
```

> 查看对应的数据结点是否正确

```sh
[root@Slave03 sbin]# hdfs dfsadmin -report
Configured Capacity: 160982630400 (149.93 GB)
Present Capacity: 82032381952 (76.40 GB)
DFS Remaining: 82032345088 (76.40 GB)
DFS Used: 36864 (36 KB)
DFS Used%: 0.00%
Replicated Blocks:
        Under replicated blocks: 0
        Blocks with corrupt replicas: 0
        Missing blocks: 0
        Missing blocks (with replication factor 1): 0
        Low redundancy blocks with highest priority to recover: 0
        Pending deletion blocks: 0
Erasure Coded Block Groups: 
        Low redundancy block groups: 0
        Block groups with corrupt internal blocks: 0
```

* hdfs主页`Slave03:50070`，如果不行，试试默认端口`9870`
* hadoop主页`Slave03:8088`

### 主要命令

* 启动集群: `./sbin/start-all.sh`
* 关闭集群: `./sbin/stop-all.sh`



## spark安装

### 安装配置

> 下载解压，复制到`/usr/spark-3.0`

> 配置环境变量`vi /etc/profile`

```sh
# 配置spark环境
export SPARK_HOME=/usr/spark-3.0
export PATH=$PATH:$SPARK_HOME/bin
```

执行`source /etc/profile`令其生效

> 配置`spark-env.sh`
>
> 将`conf`文件夹下的`spark-env.sh.template`重命名为`spark-env.sh`，并添加以下内容：

```sh
# 环境变量
export JAVA_HOME=/usr/java/jdk1.8.0_281-amd64
export SCALA_HOME=/usr/share/scala
export HADOOP_HOME=/usr/hadoop-3.2.1
# 详细配置
export HADOOP_CONF_DIR=$HADOOP_HOME/etc/hadoop
export SPARK_MASTER_HOST=Slave03
export SPARK_LOCAL_DIRS=/usr/spark-3.0
export SPARK_DRIVER_MEMORY=16g  #内存
export SPARK_EXECUTOR_MEMORY=8g  # 执行内存
export SPARK_WORKER_CORES=2     #cpus核心数
```

> 配置`slaves`
>
> 将`conf`文件夹下的`slaves.template`重命名为`slaves`，并添加以下内容：

```sh
Slave00
Slave02
Slave03  # 主节点
```

> 配置从节点(将spark目录复制到其他节点的同一个目录下)

```sh
scp -r root@192.168.123.24:/usr/spark-3.0 /usr/
```

> 在`sbin`目录下使用`start-all.sh`启动集群

![image-20220125113759221](res/Spark%E9%9B%86%E7%BE%A4%E6%90%AD%E5%BB%BA/image-20220125113759221.png)

启动成功后，我们在浏览器输入`Slave03:8080`看到有三个结点，就代表我们安装成功了。
如果发现启动错误，请查看`logs`目录下的日志，自行检查配置文件！



### 主要命令

* 启动集群: `./sbin/start-all.sh`
* 关闭集群: `./sbin/stop-all.sh`

# pyspark

## 环境配置

> spark集群服务器配置环境变量，`vi /etc/profile`

```sh
export PYSPARK_PYTHON=/root/anaconda3/envs/ray37/bin/python3.7
export PYSPARK_DRIVER_PYTHON=/root/anaconda3/envs/ray37/bin/python3.7
```

执行`source /etc/profile`使其生效

> 安装pyspark库

```sh
pip install pyspark
```

> 对于spark服务器和work间环境不一致的情况

* **方式一**: 重新安装python虚拟环境，使得路径完全一致

* **方式二**: 配置软链接

  ```sh
  ln -s 源文件 目标文件
  # 需要实现建好路径
  ln -s /opt/anaconda/install/envs/ray37/bin/python3.7 /root/anaconda3/envs/ray37/bin/python3.7
  ```



## 配置远程解释器

### 配置连接

> 配置连接的远程服务器

打开`Pycharm -> Tools -> Deployment -> Configuration`

> 点击加号，选择SFTP，给服务器取一个名字

> 点击SSH configuration后面的省略号
>
> 输入`服务器IP地址`和`账号密码`，结束后测试一下连接情况
>
> `Connection Parameters`下的心跳可以设置10秒
>
> 确认后，保存退出当前窗口

> 点击自动检测 ，选择远程服务器的工作路径
>
> 这里选的是，自己提前新建的空文件夹`/home/huangyc/hyc_test`

> `Mappings`下选择`Local path`，配置当前项目的路径
>
> 保存，退出

### 配置解释器

> 选择远程的解释器

<center><img src="res/Spark%E9%9B%86%E7%BE%A4%E6%90%AD%E5%BB%BA/image-20220127143544475.png" width=600></center>

> 点击move即可next
> 配置路径，远程的选择自己之前新建的`/home/huangyc/hyc_test`
> `Automatically upload ...`表示会自动上传项目到服务器中

<center><img src="res/Spark%E9%9B%86%E7%BE%A4%E6%90%AD%E5%BB%BA/image-20220127143940559.png" width=600></center>



### 其它功能

* 在`Pycharm`上显示远程代码：选择`Tools -->Deployment-->Browse Remote Host`
* 更新代码：将本地代码上传到服务器上`Tools -->Deployment-->upload to`
* 服务器上代码下载到本地代码上`Tools -->Deployment-->Download from`

## 测试例子

```python
#!/usr/bin/env Python
# -- coding: utf-8 --

"""
@version: v1.0
@author: huangyc
@file: connenct_test.py
@Description: 
@time: 2022/1/25 14:49
"""

from pyspark import SparkConf
from pyspark.sql import SparkSession
import traceback
import os

# 配置服务器的python解释器的路径
# 没配的情况下可能出错，正常来说配置了PYSPARK_PYTHON和PYSPARK_DRIVER_PYTHON应该可以
os.environ["PYSPARK_PYTHON"] = "/root/anaconda3/envs/ray37/bin/python3.7"

appname = "test_hyc"  # 任务名称
master = "spark://192.168.xx.xx:7077"  # 单机模式设置

'''
local: 所有计算都运行在一个线程当中，没有任何并行计算，通常我们在本机执行一些测试代码，或者练手，就用这种模式。
local[K]: 指定使用几个线程来运行计算，比如local[4]就是运行4个worker线程。通常我们的cpu有几个core，就指定几个线程，最大化利用cpu的计算能力
local[*]: 这种模式直接帮你按照cpu最多cores来设置线程数了。
'''

if __name__ == '__main__':
    try:
        conf = SparkConf().setAppName(appname).setMaster(master)  # spark资源配置
        spark = SparkSession.builder.config(conf=conf).getOrCreate()
        sc = spark.sparkContext
        words = sc.parallelize(
            ["scala", "java", "hadoop", "spark", "akka", "spark vs hadoop", "pyspark", "pyspark and spark"])
        counts = words.count()
        print("Number of elements in RDD is %i" % counts)
        sc.stop()
        print('计算成功！')
    except:
        sc.stop()
        traceback.print_exc()  # 返回出错信息

22/01/27 14:11:18 WARN NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
Using Spark's default log4j profile: org/apache/spark/log4j-defaults.properties
Setting default log level to "WARN".
To adjust logging level use sc.setLogLevel(newLevel). For SparkR, use setLogLevel(newLevel).
Number of elements in RDD is 8
计算成功！
```





# 常见问题






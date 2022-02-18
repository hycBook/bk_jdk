# node_modules_mathjax
> 用于存放gitbook的node插件，原则上gitbook会去下载最新的插件

> 主要用于应对**mathjax插件**，原因是该插件没有在维护更新，所以自己备份了一个库

## 文件夹介绍

### fonts

> 用于存放字体，给gitbook导出电子书时使用

### node_modules

> gitbook的node插件

### py_scripts

> 存放一些辅助的python脚本

* **gen_sup_idx.py**: 用于更新辅助列的编号以及`[TOC]`标签的解析

### styles

> 存放自定义的style文件

* **style.css**: 修改page-inner的宽度为1000(原始值为800)，这里感觉800的话界面太窄，不太美观

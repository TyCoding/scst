# 项目安装

## 开发环境

| Name | Version |
| -- | -- |
| OS | MacOS 10.15 |
| Mysql | 5.7 |
| IDEA | 2020.1 |
| JDK | 1.8 |
| Maven | 3.6.3 |

## 下载项目

如果你本地安装了Git客户端，可以直接使用`git clone`命令下载，或者你可以直接下载`zip`压缩包文件：

1. 使用`git clone`命令下载项目:

![截屏2020-07-18 上午7.09.52](http://cdn.tycoding.cn/20200718070954.png)

2. 直接在`zip`压缩文件

![image-20200718071027472](http://cdn.tycoding.cn/20200718071027.png)

## 本地配置

修改本地`hosts`文件，添加如下配置：

```
# SCST Project
127.0.0.1 scst-nacos
127.0.0.1 scst-auth
127.0.0.1 scst-gateway
127.0.0.1 scst-mysql
127.0.0.1 scst-sentinel
```

## Nacos

```properties
spring.datasource.platform=mysql

db.num=1
db.url.0=jdbc:mysql://127.0.0.1:3306/scst_nacos_config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
db.user=root
db.password=root
```

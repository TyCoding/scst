# Install

## Environment

**Base**

| Name | Version |
| -- | -- |
| OS | MacOS 10.15 |
| Mysql | 5.7 |
| IDEA | 2020.1 |
| JDK | 1.8 |
| Maven | 3.6.3 |

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

package cn.tycoding.scst.component;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Component 组件模块，已实现了如下组件服务
 * <p>
 * `email` -- 邮件服务，基于`spring-boot-starter-mail`
 * `alipay` -- 支付宝支付，基于支付宝模拟沙箱实现
 * `chat` -- 在线聊天，基于websocket实现在线聊天功能
 *
 * @author tycoding
 * @date 2019-06-09
 */
@EnableEurekaClient
@SpringBootApplication
public class ScstComponentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScstComponentApplication.class, args);
    }
}

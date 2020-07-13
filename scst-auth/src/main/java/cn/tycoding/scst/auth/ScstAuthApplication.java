package cn.tycoding.scst.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 鉴权模块
 *
 * @author tycoding
 * @date 2020/7/12
 */
@EnableFeignClients("cn.tycoding.scst.system.api.feign")
@SpringCloudApplication
public class ScstAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScstAuthApplication.class, args);
    }
}

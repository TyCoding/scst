package cn.tycoding.scst.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 身份校验 auth
 *
 * @author tycoding
 * @date 2019-06-08
 */
@EnableFeignClients("cn.tycoding.scst.system.api.feign")
@EnableEurekaClient
@SpringBootApplication
public class ScstAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScstAuthApplication.class, args);
    }
}

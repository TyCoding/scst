package cn.tycoding.scst.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka注册中心
 *
 * @author tycoding
 * @date 2019-06-02
 */
@EnableEurekaServer
@SpringBootApplication
public class ScstEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScstEurekaApplication.class, args);
    }
}

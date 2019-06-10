package cn.tycoding.scst.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 分布式监控中心 SpringBootAdmin 服务端
 *
 * @author tycoding
 * @date 2019-08-08
 */
@EnableAdminServer
@EnableEurekaClient
@SpringBootApplication
public class ScstAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScstAdminApplication.class, args);
    }
}

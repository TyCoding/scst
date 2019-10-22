package cn.tycoding.scst.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin.server.internal.EnableZipkinServer;

/**
 * 链路追踪 Zipkin
 *
 * @author tycoding
 * @date 2019-06-19
 */
@EnableZipkinServer
@EnableEurekaClient
@SpringBootApplication
public class ScstZipkinApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScstZipkinApplication.class, args);
    }
}

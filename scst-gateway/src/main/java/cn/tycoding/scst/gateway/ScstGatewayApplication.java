package cn.tycoding.scst.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * API网关 Zuul
 *
 * @author tycoding
 * @date 2019-06-08
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ScstGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScstGatewayApplication.class, args);
    }
}

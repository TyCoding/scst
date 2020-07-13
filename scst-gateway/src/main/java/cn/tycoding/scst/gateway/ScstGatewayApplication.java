package cn.tycoding.scst.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * Spring Cloud Gateway 路由网关
 *
 * @author tycoding
 * @date 2020/7/13
 */
@SpringCloudApplication
public class ScstGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScstGatewayApplication.class, args);
    }
}

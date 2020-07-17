package cn.tycoding.scst.system.biz;

import cn.tycoding.scst.common.security.annotation.EnableScstResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 系统权限管理模块
 *
 * @author tycoding
 * @date 2020/7/13
 */
@EnableScstResourceServer
@EnableFeignClients
@SpringCloudApplication
public class SctSystemBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(SctSystemBizApplication.class, args);
    }
}

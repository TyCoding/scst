package cn.tycoding.scst.common.log.config;

import cn.tycoding.scst.common.log.event.SysLogListener;
import cn.tycoding.scst.common.log.aspect.SysLogAspect;
import cn.tycoding.scst.system.api.feign.RemoteLogService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author tycoding
 * @date 2019-06-09
 */
@EnableAsync
@Configuration
@AllArgsConstructor
@ConditionalOnWebApplication
@EnableFeignClients({"cn.tycoding.scst.system.api.feign"})
public class LogAutoConfig {

    private final RemoteLogService remoteLogService;

    @Bean
    public SysLogListener sysLogListener() {
        return new SysLogListener(remoteLogService);
    }

    @Bean
    public SysLogAspect sysLogAspect() {
        return new SysLogAspect();
    }

}

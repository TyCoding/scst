package cn.tycoding.scst.system.api.feign;

import cn.tycoding.scst.common.constant.ServiceNameConstants;
import cn.tycoding.scst.common.utils.R;
import cn.tycoding.scst.system.api.entity.SysLog;
import cn.tycoding.scst.system.api.feign.fallback.RemoteLogServiceFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * Feign系统日志远程调用接口
 *
 * @author tycoding
 * @date 2019-06-09
 */
@FeignClient(value = ServiceNameConstants.SYSTEM_SERVICE, fallback = RemoteLogServiceFallbackImpl.class)
public interface RemoteLogService {

    @PostMapping("/log")
    R saveLog(@RequestBody SysLog sysLog);
}

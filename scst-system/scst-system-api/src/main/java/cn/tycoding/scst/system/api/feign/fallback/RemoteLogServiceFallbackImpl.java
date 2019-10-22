package cn.tycoding.scst.system.api.feign.fallback;

import cn.tycoding.scst.common.core.utils.R;
import cn.tycoding.scst.system.api.entity.SysLog;
import cn.tycoding.scst.system.api.feign.RemoteLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author tycoding
 * @date 2019-06-09
 */
@Slf4j
@Component
public class RemoteLogServiceFallbackImpl implements RemoteLogService {

    @Override
    public R saveLog(SysLog sysLog) {
        log.error("feign 日志插入失败...");
        return null;
    }
}

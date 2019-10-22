package cn.tycoding.scst.system.api.feign;

import cn.tycoding.scst.common.core.constant.ServiceNameConstants;
import cn.tycoding.scst.common.core.utils.R;
import cn.tycoding.scst.system.api.dto.UserInfo;
import cn.tycoding.scst.system.api.feign.fallback.RemoteUserServiceFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author tycoding
 * @date 2019-06-19
 */
@FeignClient(value = ServiceNameConstants.SYSTEM_SERVICE, fallback = RemoteUserServiceFallbackImpl.class)
public interface RemoteUserService {

    @GetMapping("/user/info/{username}")
    R<UserInfo> info(@PathVariable("username") String username);
}

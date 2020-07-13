package cn.tycoding.scst.system.api.feign.fallback;

import cn.tycoding.scst.common.core.utils.R;
import cn.tycoding.scst.system.api.dto.UserInfo;
import cn.tycoding.scst.system.api.feign.RemoteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @date 2020/7/13
 */
@Slf4j
@Service
public class RemoteUserServiceFallbackImpl implements RemoteUserService {

    @Override
    public R<UserInfo> info(String username) {
        log.error("查询用户信息失败 >>>>>>>>>>> username = {}", username);
        return null;
    }
}

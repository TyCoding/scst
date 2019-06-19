package cn.tycoding.scst.auth.controller;

import cn.tycoding.scst.common.utils.R;
import cn.tycoding.scst.system.api.entity.SysUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tycoding
 * @date 2019-06-04
 */
@RestController
public class LoginController {

    @PostMapping("/oauth/token")
    public R login(@RequestBody SysUser user) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", user.getUsername());
        return new R(map);
    }

    @GetMapping("/user/logout")
    public R logout() {
        return new R();
    }
}

package cn.tycoding.scst.system.biz.controller;

import cn.tycoding.scst.common.utils.Result;
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
    public Result login(@RequestBody SysUser user) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", user.getUsername());
        return new Result(map);
    }

    @GetMapping("/user/logout")
    public Result logout() {
        return new Result();
    }
}

package cn.tycoding.scst.system.biz.controller;

import cn.tycoding.scst.common.core.constant.enums.CommonEnums;
import cn.tycoding.scst.common.core.controller.BaseController;
import cn.tycoding.scst.common.core.utils.QueryPage;
import cn.tycoding.scst.common.core.utils.R;
import cn.tycoding.scst.common.log.annotation.Log;
import cn.tycoding.scst.common.security.utils.SecurityUtil;
import cn.tycoding.scst.system.api.dto.UserInfo;
import cn.tycoding.scst.system.api.entity.SysUser;
import cn.tycoding.scst.system.api.entity.SysUserWithRole;
import cn.tycoding.scst.system.biz.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户接口
 *
 * @author tycoding
 * @date 2019-06-02
 */
@RestController
@RequestMapping("/user")
@Api(value = "SysUserController", tags = {"用户管理接口"})
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取当前用户信息
     *
     * @return
     */
    @GetMapping("/info")
    public R<UserInfo> info() {
        String username = SecurityUtil.getUsername();
        SysUser user = sysUserService.findByName(username);
        if (user == null) {
            return new R<>(CommonEnums.USER_ERROR);
        }
        return new R<>(sysUserService.getUserInfo(user));
    }

    @GetMapping("/info/{username}")
    public R<UserInfo> info(@PathVariable("username") String username) {
        SysUser user = sysUserService.findByName(username);
        if (user == null) {
            return new R<>(CommonEnums.USER_ERROR);
        }
        return new R<>(sysUserService.getUserInfo(user));
    }

    @PostMapping("/list")
    public R list(@RequestBody SysUser user, QueryPage queryPage) {
        return new R<>(super.getData(sysUserService.list(user, queryPage)));
    }

    /**
     * 根据用户名获取对应的权限列表
     *
     * @param username
     * @return
     */
    @GetMapping("/getMenus/{username}")
    public R<List> getMenus(@PathVariable("username") String username) {
        return new R<>(sysUserService.getMenus(username));
    }

    @GetMapping("/{id}")
    public R<SysUser> findById(@PathVariable Long id) {
        if (id == null || id == 0) {
            return new R<>();
        } else {
            return new R<>(sysUserService.findById(id));
        }
    }

    @Log("添加用户")
    @PostMapping
    public R add(@RequestBody SysUserWithRole user) {
        sysUserService.add(user);
        return new R();
    }

    @Log("删除用户")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        sysUserService.delete(id);
        return new R();
    }

    @Log("更新用户")
    @PutMapping
    public R edit(@RequestBody SysUserWithRole user) {
        sysUserService.update(user);
        return new R();
    }

    @Log("修改密码")
    @PostMapping("/changePass")
    public R changePass(String password) {
        sysUserService.updatePassword(password);
        return new R();
    }

    @GetMapping("/checkName/{name}/{id}")
    public R<Boolean> checkName(@PathVariable("name") String name, @PathVariable("id") String id) {
        return new R<>(sysUserService.checkName(name, id));
    }

}

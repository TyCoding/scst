package cn.tycoding.scst.system.biz.controller;

import cn.tycoding.scst.common.controller.BaseController;
import cn.tycoding.scst.common.utils.QueryPage;
import cn.tycoding.scst.common.utils.R;
import cn.tycoding.scst.common.log.annotation.Log;
import cn.tycoding.scst.system.api.entity.*;
import cn.tycoding.scst.system.biz.service.SysDeptService;
import cn.tycoding.scst.system.biz.service.SysMenuService;
import cn.tycoding.scst.system.biz.service.SysRoleService;
import cn.tycoding.scst.system.biz.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 获取当前用户信息
     *
     * @return
     */
    @GetMapping("/info")
    @ApiOperation(value = "获取当前授权用户信息", notes = "必须经过了OAuth授权")
    public R<Map> info() {
//        String username = SecurityUtils.getUsername();
//        SysUser user = sysUserService.findByName(username);
//        if (user == null) {
//            return new R<>(CommonEnums.USER_ERROR);
//        }
//        return new R<>(user);

        SysUser user = SysUser.builder()
                .id(1L)
                .username("admin")
                .avatar("http://cdn.tycoding.cn/author.png")
                .deptId(3L)
                .description("管理员账号")
                .build();

        //获取用户角色列表
        List<SysRole> roleList = sysRoleService.findUserRoles(user.getUsername());
        Set<String> roleSet = roleList.stream().map(SysRole::getName).collect(Collectors.toSet());

        //获取用户权限列表
        List<SysMenu> menuList = sysMenuService.findUserPermissions(user.getUsername());
        Set<String> menuSet = menuList.stream().map(SysMenu::getPermission).collect(Collectors.toSet());

        //获取用户部门列表
        SysDept dept = sysDeptService.findById(user.getDeptId());

        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("name", user.getUsername());
        map.put("avatar", user.getAvatar());
        map.put("roles", roleSet);
        map.put("permission", menuSet);
        map.put("dept", dept);
        map.put("description", user.getDescription());
        return new R<>(map);
    }

    @GetMapping("/info/{username}")
    @ApiOperation(value = "根据用户名查询用户信息")
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String")
    public R<SysUser> info(@PathVariable("username") String username) {
        return new R<>(sysUserService.findByName(username));
    }

    @PostMapping("/list")
    @ApiOperation(value = "分页、条件查询用户列表信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "查询条件", required = true, dataType = "SysUser", paramType = "body"),
            @ApiImplicitParam(name = "queryPage", value = "分页条件", required = true, dataType = "QueryPage", paramType = "body")
    })
    public R<Map> list(@RequestBody SysUser user, QueryPage queryPage) {
        return new R<>(this.selectByPageNumSize(queryPage, () -> sysUserService.list(user)));
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
    @ApiOperation(value = "查询详细用户信息", notes = "id存在且大于0")
    @ApiImplicitParam(name = "id", value = "用户编号", required = true, dataType = "Long")
    public R<SysUser> findById(@PathVariable Long id) {
        if (id == null || id == 0) {
            return new R<>();
        } else {
            return new R<>(sysUserService.findById(id));
        }
    }

    @Log("添加用户")
    @PostMapping
    @ApiOperation(value = "添加用户")
    @ApiImplicitParam(name = "user", value = "用户实体信息", required = true, dataType = "SysUserWithRole", paramType = "body")
    public R add(@RequestBody SysUserWithRole user) {
        sysUserService.add(user);
        return new R();
    }

    @Log("删除用户")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户")
    @ApiImplicitParam(name = "id", value = "用户编号", required = true, dataType = "Long")
    public R delete(@PathVariable Long id) {
        sysUserService.delete(id);
        return new R();
    }

    @Log("更新用户")
    @PutMapping
    @ApiOperation(value = "更新用户")
    @ApiImplicitParam(name = "user", value = "用户实体信息", required = true, dataType = "SysUserWithRole", paramType = "body")
    public R edit(@RequestBody SysUserWithRole user) {
        sysUserService.update(user);
        return new R();
    }

    @Log("修改密码")
    @PostMapping("/changePass")
    @ApiOperation(value = "修改密码")
    public R changePass(String password) {
        sysUserService.updatePassword(password);
        return new R();
    }

    @GetMapping("/checkName/{name}/{id}")
    @ApiOperation(value = "校验该名称是否已存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "String")
    })
    public R<Boolean> checkName(@PathVariable("name") String name, @PathVariable("id") String id) {
        return new R<>(sysUserService.checkName(name, id));
    }

}

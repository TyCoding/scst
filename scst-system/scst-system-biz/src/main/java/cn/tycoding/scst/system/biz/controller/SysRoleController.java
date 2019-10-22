package cn.tycoding.scst.system.biz.controller;

import cn.tycoding.scst.common.core.controller.BaseController;
import cn.tycoding.scst.common.core.utils.QueryPage;
import cn.tycoding.scst.common.core.utils.R;
import cn.tycoding.scst.common.log.annotation.Log;
import cn.tycoding.scst.system.api.entity.SysRole;
import cn.tycoding.scst.system.api.entity.SysRoleWithMenu;
import cn.tycoding.scst.system.biz.service.SysRoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tycoding
 * @date 2019-06-03
 */
@RestController
@RequestMapping("/role")
@Api(value = "SysRoleController", tags = {"角色管理接口"})
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping("/list")
    public R list(@RequestBody SysRole role, QueryPage queryPage) {
        return new R<>(super.getData(sysRoleService.list(role, queryPage)));
    }

    @GetMapping("/{id}")
    public R<SysRoleWithMenu> findById(@PathVariable Long id) {
        if (id == null || id == 0) {
            return new R<>();
        } else {
            return new R<>(sysRoleService.findById(id));
        }
    }

    @Log("添加角色")
    @PostMapping
    public R add(@RequestBody SysRoleWithMenu role) {
        sysRoleService.add(role);
        return new R();
    }

    @Log("删除角色")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        sysRoleService.delete(id);
        return new R();
    }

    @Log("更新角色")
    @PutMapping
    public R edit(@RequestBody SysRoleWithMenu role) {
        sysRoleService.update(role);
        return new R();
    }

    @GetMapping("/checkName/{name}/{id}")
    public R<Boolean> checkName(@PathVariable("name") String name, @PathVariable("id") String id) {
        return new R<>(sysRoleService.checkName(name, id));
    }
}

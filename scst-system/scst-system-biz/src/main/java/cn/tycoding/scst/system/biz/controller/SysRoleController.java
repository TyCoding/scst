package cn.tycoding.scst.system.biz.controller;

import cn.tycoding.scst.common.controller.BaseController;
import cn.tycoding.scst.common.utils.QueryPage;
import cn.tycoding.scst.common.utils.R;
import cn.tycoding.scst.common.log.annotation.Log;
import cn.tycoding.scst.system.api.entity.SysRole;
import cn.tycoding.scst.system.api.entity.SysRoleWithMenu;
import cn.tycoding.scst.system.biz.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    @ApiOperation(value = "分页、条件查询角色列表信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "role", value = "查询条件", required = true, dataType = "SysRole", paramType = "body"),
            @ApiImplicitParam(name = "queryPage", value = "分页条件", required = true, dataType = "QueryPage", paramType = "body")
    })
    public R<Map> list(@RequestBody SysRole role, QueryPage queryPage) {
        return new R<>(this.selectByPageNumSize(queryPage, () -> sysRoleService.list(role)));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询详细角色信息", notes = "id存在且大于0")
    @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "Long")
    public R<SysRoleWithMenu> findById(@PathVariable Long id) {
        if (id == null || id == 0) {
            return new R<>();
        } else {
            return new R<>(sysRoleService.findById(id));
        }
    }

    @Log("添加角色")
    @PostMapping
    @ApiOperation(value = "添加角色")
    @ApiImplicitParam(name = "user", value = "角色实体信息", required = true, dataType = "SysRoleWithMenu", paramType = "body")
    public R add(@RequestBody SysRoleWithMenu role) {
        sysRoleService.add(role);
        return new R();
    }

    @Log("删除角色")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除角色")
    @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "Long")
    public R delete(@PathVariable Long id) {
        sysRoleService.delete(id);
        return new R();
    }

    @Log("更新角色")
    @PutMapping
    @ApiOperation(value = "更新角色")
    @ApiImplicitParam(name = "role", value = "角色实体信息", required = true, dataType = "SysRoleWithMenu", paramType = "body")
    public R edit(@RequestBody SysRoleWithMenu role) {
        sysRoleService.update(role);
        return new R();
    }

    @GetMapping("/checkName/{name}/{id}")
    @ApiOperation(value = "校验该名称是否已存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "String")
    })
    public R<Boolean> checkName(@PathVariable("name") String name, @PathVariable("id") String id) {
        return new R<>(sysRoleService.checkName(name, id));
    }
}

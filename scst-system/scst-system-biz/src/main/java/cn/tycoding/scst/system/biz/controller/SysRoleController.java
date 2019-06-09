package cn.tycoding.scst.system.biz.controller;

import cn.tycoding.scst.common.controller.BaseController;
import cn.tycoding.scst.common.utils.QueryPage;
import cn.tycoding.scst.common.utils.Result;
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
    public Result<Map> list(@RequestBody SysRole role, QueryPage queryPage) {
        return new Result<>(this.selectByPageNumSize(queryPage, () -> sysRoleService.list(role)));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询详细角色信息", notes = "id存在且大于0")
    @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "Long")
    public Result<SysRoleWithMenu> findById(@PathVariable Long id) {
        if (id == null || id == 0) {
            return new Result<>();
        } else {
            return new Result<>(sysRoleService.findById(id));
        }
    }

    @Log("添加角色")
    @PostMapping
    @ApiOperation(value = "添加角色")
    @ApiImplicitParam(name = "user", value = "角色实体信息", required = true, dataType = "SysRoleWithMenu", paramType = "body")
    public Result add(@RequestBody SysRoleWithMenu role) {
        sysRoleService.add(role);
        return new Result();
    }

    @Log("删除角色")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除角色")
    @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "Long")
    public Result delete(@PathVariable Long id) {
        sysRoleService.delete(id);
        return new Result();
    }

    @Log("更新角色")
    @PutMapping
    @ApiOperation(value = "更新角色")
    @ApiImplicitParam(name = "role", value = "角色实体信息", required = true, dataType = "SysRoleWithMenu", paramType = "body")
    public Result edit(@RequestBody SysRoleWithMenu role) {
        sysRoleService.update(role);
        return new Result();
    }

    @GetMapping("/checkName/{name}/{id}")
    @ApiOperation(value = "校验该名称是否已存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "String")
    })
    public Result<Boolean> checkName(@PathVariable("name") String name, @PathVariable("id") String id) {
        return new Result<>(sysRoleService.checkName(name, id));
    }
}

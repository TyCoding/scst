package cn.tycoding.scst.system.biz.controller;

import cn.tycoding.scst.common.controller.BaseController;
import cn.tycoding.scst.common.utils.QueryPage;
import cn.tycoding.scst.common.utils.R;
import cn.tycoding.scst.common.log.annotation.Log;
import cn.tycoding.scst.system.api.entity.SysMenu;
import cn.tycoding.scst.system.biz.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author tycoding
 * @date 2019-06-03
 */
@RestController
@RequestMapping("/menu")
@Api(value = "SysMenuController", tags = {"权限管理接口"})
public class SysMenuController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;

    @PostMapping("/list")
    @ApiOperation(value = "分页、条件查询权限列表信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menu", value = "查询条件", required = true, dataType = "SysMenu", paramType = "body"),
            @ApiImplicitParam(name = "queryPage", value = "分页条件", required = true, dataType = "QueryPage", paramType = "body")
    })
    public R<Map> list(@RequestBody SysMenu menu, QueryPage queryPage) {
        return new R<>(this.selectByPageNumSize(queryPage, () -> sysMenuService.list(menu)));
    }

    @GetMapping("/tree")
    @ApiOperation(value = "获取菜单权限Tree树")
    public R<List> tree() {
        return new R<>(sysMenuService.tree());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询权限信息", notes = "id存在且大于0")
    @ApiImplicitParam(name = "id", value = "权限ID", required = true, dataType = "Long")
    public R<SysMenu> findById(@PathVariable Long id) {
        if (id == null || id == 0) {
            return new R<>();
        } else {
            return new R<>(sysMenuService.findById(id));
        }
    }

    @Log("添加权限")
    @PostMapping
    @ApiOperation(value = "添加权限")
    @ApiImplicitParam(name = "menu", value = "菜单实体信息", required = true, dataType = "SysMenu", paramType = "body")
    public R add(@RequestBody SysMenu menu) {
        sysMenuService.add(menu);
        return new R();
    }

    @Log("删除权限")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除权限")
    @ApiImplicitParam(name = "id", value = "权限ID", required = true, dataType = "Long")
    public R delete(@PathVariable Long id) {
        sysMenuService.delete(id);
        return new R();
    }

    @Log("更新权限")
    @PutMapping
    @ApiOperation(value = "更新权限")
    @ApiImplicitParam(name = "menu", value = "权限实体信息", required = true, dataType = "SysMenu", paramType = "body")
    public R edit(@RequestBody SysMenu menu) {
        sysMenuService.update(menu);
        return new R();
    }

    @GetMapping("/checkName/{name}/{id}")
    @ApiOperation(value = "校验该名称是否已存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "String")
    })
    public R<Boolean> checkName(@PathVariable("name") String name, @PathVariable("id") String id) {
        return new R<>(sysMenuService.checkName(name, id));
    }
}

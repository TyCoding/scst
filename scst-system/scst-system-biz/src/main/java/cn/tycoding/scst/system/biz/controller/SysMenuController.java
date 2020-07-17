package cn.tycoding.scst.system.biz.controller;

import cn.tycoding.scst.common.core.utils.R;
import cn.tycoding.scst.common.log.annotation.Log;
import cn.tycoding.scst.common.web.controller.BaseController;
import cn.tycoding.scst.common.web.utils.QueryPage;
import cn.tycoding.scst.system.api.entity.SysMenu;
import cn.tycoding.scst.system.biz.service.SysMenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tycoding
 * @date 2020/7/13
 */
@RestController
@RequestMapping("/menu")
@Api(value = "SysMenuController", tags = {"菜单管理接口"})
public class SysMenuController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;

    @PostMapping("/list")
    public R list(@RequestBody SysMenu sysMenu, QueryPage queryPage) {
        return new R<>(super.getData(sysMenuService.list(sysMenu, queryPage)));
    }

    @PostMapping("/filter/list")
    public R list(@RequestBody SysMenu sysMenu) {
        return new R<>(sysMenuService.list(sysMenu));
    }

    @GetMapping("/tree")
    public R tree() {
        return new R<>(sysMenuService.tree());
    }

    /**
     * 加载系统左侧权限菜单
     *
     * @return
     */
    @GetMapping("/build")
    public R build() {
        return new R<>(sysMenuService.build());
    }

    @GetMapping("/{id}")
    public R findById(@PathVariable Long id) {
        if (id == null || id == 0) {
            return new R<>();
        } else {
            return new R<>(sysMenuService.getById(id));
        }
    }

    @Log("添加权限")
    @PostMapping
    public R add(@RequestBody SysMenu sysMenu) {
        sysMenuService.add(sysMenu);
        return new R();
    }

    @Log("删除权限")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        sysMenuService.delete(id);
        return new R();
    }

    @Log("更新权限")
    @PutMapping
    public R edit(@RequestBody SysMenu sysMenu) {
        sysMenuService.update(sysMenu);
        return new R();
    }

    @GetMapping("/checkName/{name}/{id}")
    public R checkName(@PathVariable("name") String name, @PathVariable("id") String id) {
        return new R<>(sysMenuService.checkName(name, id));
    }
}

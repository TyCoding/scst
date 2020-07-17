package cn.tycoding.scst.system.biz.controller;

import cn.tycoding.scst.common.core.utils.R;
import cn.tycoding.scst.common.log.annotation.Log;
import cn.tycoding.scst.common.web.controller.BaseController;
import cn.tycoding.scst.common.web.utils.QueryPage;
import cn.tycoding.scst.system.api.entity.SysDept;
import cn.tycoding.scst.system.biz.service.SysDeptService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tycoding
 * @date 2020/7/13
 */
@RestController
@RequestMapping("/dept")
@Api(value = "SysDeptController", tags = {"部门管理接口"})
public class SysDeptController extends BaseController {

    @Autowired
    private SysDeptService sysDeptService;

    @PostMapping("/list")
    public R list(SysDept sysDept, QueryPage queryPage) {
        return new R<>(super.getData(sysDeptService.list(sysDept, queryPage)));
    }

    @PostMapping("/filter/list")
    public R list(@RequestBody SysDept sysDept) {
        return new R<>(sysDeptService.list(sysDept));
    }

    @GetMapping("/tree")
    public R tree() {
        return new R<>(sysDeptService.tree());
    }

    @GetMapping("/{id}")
    public R findById(@PathVariable Long id) {
        if (id == null || id == 0) {
            return new R<>();
        } else {
            return new R<>(sysDeptService.getById(id));
        }
    }

    @Log("添加部门")
    @PostMapping
    public R add(@RequestBody SysDept sysDept) {
        sysDeptService.add(sysDept);
        return new R();
    }

    @Log("删除部门")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        sysDeptService.delete(id);
        return new R();
    }

    @Log("更新部门")
    @PutMapping
    public R edit(@RequestBody SysDept sysDept) {
        sysDeptService.update(sysDept);
        return new R();
    }

    @GetMapping("/checkName/{name}/{id}")
    public R<Boolean> checkName(@PathVariable("name") String name, @PathVariable("id") String id) {
        return new R<>(sysDeptService.checkName(name, id));
    }
}

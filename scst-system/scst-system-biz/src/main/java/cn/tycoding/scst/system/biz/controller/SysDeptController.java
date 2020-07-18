package cn.tycoding.scst.system.biz.controller;

import cn.tycoding.scst.common.core.utils.R;
import cn.tycoding.scst.common.log.annotation.Log;
import cn.tycoding.scst.common.web.controller.BaseController;
import cn.tycoding.scst.system.api.entity.SysDept;
import cn.tycoding.scst.system.biz.service.SysDeptService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 部门表 前端控制器
 *
 * @author tycoding
 * @date 2020/7/13
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dept")
@Api(value = "SysDeptController", tags = {"部门管理模块"})
public class SysDeptController extends BaseController {

    private final SysDeptService sysDeptService;

    /**
     * 条件查询
     *
     * @param sysDept 查询条件
     * @return List
     */
    @PostMapping("/filter/list")
    public R list(@RequestBody SysDept sysDept) {
        return new R<>(sysDeptService.list(sysDept));
    }

    /**
     * 构建部门列表Tree树
     *
     * @return List
     */
    @GetMapping("/tree")
    public R tree() {
        return new R<>(sysDeptService.tree());
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R findById(@PathVariable Long id) {
        if (id == null || id == 0) {
            return new R<>();
        } else {
            return new R<>(sysDeptService.getById(id));
        }
    }

    /**
     * 校验当前名称是否已存在
     *
     * @param sysDept id:当前修改对象的ID
     *                name:需要校验的名称
     * @return Boolean
     */
    @PostMapping("/checkName")
    public R<Boolean> checkName(@RequestBody SysDept sysDept) {
        return new R<>(sysDeptService.checkName(sysDept));
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
}

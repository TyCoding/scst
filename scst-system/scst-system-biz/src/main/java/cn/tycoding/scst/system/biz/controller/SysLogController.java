package cn.tycoding.scst.system.biz.controller;

import cn.tycoding.scst.common.core.exception.GlobalException;
import cn.tycoding.scst.common.core.utils.R;
import cn.tycoding.scst.common.log.annotation.Log;
import cn.tycoding.scst.common.web.controller.BaseController;
import cn.tycoding.scst.common.web.utils.QueryPage;
import cn.tycoding.scst.system.api.entity.SysLog;
import cn.tycoding.scst.system.biz.service.SysLogService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 日志表 前端控制器
 *
 * @author tycoding
 * @date 2020/7/13
 */
@RestController
@AllArgsConstructor
@RequestMapping("/log")
@Api(value = "SysLogController", tags = {"日志管理模块"})
public class SysLogController extends BaseController {

    private final SysLogService sysLogService;

    /**
     * 分页、条件查询
     *
     * @param log       查询条件
     * @param queryPage 分页条件
     * @return
     */
    @PostMapping("/list")
    public R list(@RequestBody SysLog log, QueryPage queryPage) {
        return new R<>(super.getData(sysLogService.list(log, queryPage)));
    }

    @Log("删除系统日志")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") Long id) {
        try {
            sysLogService.delete(id);
            return new R();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @PostMapping
    public R add(@RequestBody SysLog sysLog) {
        sysLogService.add(sysLog);
        return new R();
    }
}

package cn.tycoding.scst.system.biz.controller;

import cn.tycoding.scst.common.core.controller.BaseController;
import cn.tycoding.scst.common.core.exception.GlobalException;
import cn.tycoding.scst.common.core.utils.QueryPage;
import cn.tycoding.scst.common.core.utils.R;
import cn.tycoding.scst.common.log.annotation.Log;
import cn.tycoding.scst.system.api.entity.SysLog;
import cn.tycoding.scst.system.biz.service.SysLogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tycoding
 * @date 2019-06-08
 */
@RestController
@RequestMapping("/log")
@Api(value = "SysLogController", tags = {"部门管理接口"})
public class SysLogController extends BaseController {

    @Autowired
    private SysLogService sysLogService;

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
    public R saveLog(@RequestBody SysLog sysLog) {
        sysLogService.saveLog(sysLog);
        return new R();
    }
}

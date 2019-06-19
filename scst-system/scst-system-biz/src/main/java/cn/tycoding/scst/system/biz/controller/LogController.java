package cn.tycoding.scst.system.biz.controller;

import cn.tycoding.scst.common.controller.BaseController;
import cn.tycoding.scst.common.exception.GlobalException;
import cn.tycoding.scst.common.log.annotation.Log;
import cn.tycoding.scst.common.utils.R;
import cn.tycoding.scst.system.api.entity.SysLog;
import cn.tycoding.scst.common.utils.QueryPage;
import cn.tycoding.scst.system.biz.service.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author tycoding
 * @date 2019-06-08
 */
@RestController
@RequestMapping("/log")
@Api(value = "LogController", tags = {"系统日志接口"})
public class LogController extends BaseController {

    @Autowired
    private SysLogService sysLogService;

    @PostMapping("/list")
    @ApiOperation(value = "分页、条件查询权限列表信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "log", value = "查询条件", required = true, dataType = "SysLog", paramType = "body"),
            @ApiImplicitParam(name = "queryPage", value = "分页条件", required = true, dataType = "QueryPage", paramType = "body")
    })
    public R<Map> list(@RequestBody SysLog log, QueryPage page) {
        return new R<>(super.selectByPageNumSize(page, () -> sysLogService.list(log)));
    }

    @Log("删除系统日志")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据ID删除系统日志", notes = "id存在且大于0")
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
    @ApiImplicitParam(name = "sysLog", value = "日志实体", required = true, dataType = "SysLog", paramType = "body")
    public R saveLog(@RequestBody SysLog sysLog) {
        sysLogService.saveLog(sysLog);
        return new R();
    }
}

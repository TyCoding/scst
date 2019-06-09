package cn.tycoding.scst.logging.controller;

import cn.tycoding.scst.common.controller.BaseController;
import cn.tycoding.scst.common.exception.GlobalException;
import cn.tycoding.scst.common.utils.QueryPage;
import cn.tycoding.scst.common.utils.Result;
import cn.tycoding.scst.logging.annotation.Log;
import cn.tycoding.scst.logging.entity.SysLog;
import cn.tycoding.scst.logging.service.SysLogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author tycoding
 * @date 2019-06-08
 */
@RestController
@RequestMapping("/log")
@Api(value = "LogController", tags = {"系统日志模块接口"})
public class LogController extends BaseController {

    @Autowired
    private SysLogService sysLogService;

    @PostMapping("/list")
    public Result<Map> list(@RequestBody SysLog log, QueryPage page) {
        return new Result<>(super.selectByPageNumSize(page, () -> sysLogService.list(log)));
    }

    @Log("删除系统日志")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Long id) {
        try {
            sysLogService.delete(id);
            return new Result();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }
}

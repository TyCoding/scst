package cn.tycoding.scst.component.email.controller;

import cn.tycoding.scst.common.log.annotation.Log;
import cn.tycoding.scst.common.core.utils.R;
import cn.tycoding.scst.component.email.entity.MailProperties;
import cn.tycoding.scst.component.email.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tycoding
 * @date 2019-06-10
 */
@RestController
@RequestMapping("/mail")
@Api(value = "MailController", tags = {"邮件服务接口"})
public class MailController {

    @Autowired
    private MailService mailService;

    @Log("发送邮件")
    @PostMapping
    @ApiOperation(value = "发送邮件")
    @ApiImplicitParam(name = "mailProperties", value = "邮件参数", required = true, dataType = "MailProperties", paramType = "body")
    public R add(@RequestBody MailProperties mailProperties) {
        try {
            mailService.send(mailProperties);
            return new R();
        } catch (Exception e) {
            e.printStackTrace();
            return new R(e);
        }
    }
}

package cn.tycoding.scst.component.chat.controller;

import cn.tycoding.scst.common.utils.Result;
import cn.tycoding.scst.component.chat.config.WebsocketServerEndpoint;
import cn.tycoding.scst.component.chat.service.ChatSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author tycoding
 * @date 2019-06-11
 */
@RestController
@RequestMapping("/chat")
@Api(value = "ChatController", tags = {"在线聊天接口"})
public class ChatController {

    @Autowired
    private ChatSessionService chatSessionService;

    @GetMapping("/push/{id}")
    @ApiOperation(value = "给指定用户推送消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "message", value = "消息内容", required = true, dataType = "String")
    })
    public Result push(@PathVariable("id") String id, String message, HttpServletRequest request) {
        try {
            request.setAttribute("id", message);
            WebsocketServerEndpoint.sendInfo(message, id);
            return new Result("推送消息成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result("推送消息失败", e);
        }
    }

    @ApiOperation(value = "获取Redis中储存的消息列表")
    @GetMapping("/message/list")
    public Result<List> getMessageList() {
        return new Result<>(chatSessionService.getMessageList());
    }
}

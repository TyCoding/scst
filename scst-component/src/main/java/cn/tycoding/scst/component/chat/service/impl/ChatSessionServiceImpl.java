package cn.tycoding.scst.component.chat.service.impl;

import cn.tycoding.scst.common.constant.CommonConstants;
import cn.tycoding.scst.component.chat.entity.ChatMessage;
import cn.tycoding.scst.component.chat.service.ChatSessionService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author tycoding
 * @date 2019-06-12
 */
@Slf4j
@Service
public class ChatSessionServiceImpl implements ChatSessionService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void saveMessage(String id, String message) {
        //TODO 根据ID查询数据库获取用户信息
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setId(id);
        chatMessage.setName("tycoding");
        chatMessage.setMessage(message);
        chatMessage.setTime(new Date());
        chatMessage.setTo(null);

        log.info("消息存入Redis： >> {}", chatMessage);

        //将会话消息储存到Redis中
        redisTemplate.boundListOps(CommonConstants.CHAT_MESSAGE_KEY).leftPush(JSON.toJSONString(chatMessage));
    }

    @Override
    public List<ChatMessage> getMessageList() {
        List<ChatMessage> result = new ArrayList<>();
        Long size = redisTemplate.boundListOps(CommonConstants.CHAT_MESSAGE_KEY).size();
        List<String> list = redisTemplate.boundListOps(CommonConstants.CHAT_MESSAGE_KEY).range(0, size);

        if (list == null || list.isEmpty()) {
            return null;
        }
        list.forEach(message -> {
            log.info("message >> {}", message);
            result.add(JSONObject.parseObject(message, ChatMessage.class));
        });
        return result;
    }
}

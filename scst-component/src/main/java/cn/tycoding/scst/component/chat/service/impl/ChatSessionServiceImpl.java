package cn.tycoding.scst.component.chat.service.impl;

import cn.tycoding.scst.common.utils.StringUtil;
import cn.tycoding.scst.component.chat.constant.ChatConstant;
import cn.tycoding.scst.component.chat.entity.Message;
import cn.tycoding.scst.component.chat.entity.User;
import cn.tycoding.scst.component.chat.service.ChatSessionService;
import cn.tycoding.scst.component.chat.utils.ChatUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author tycoding
 * @date 2019-06-14
 */
@Slf4j
@Service
public class ChatSessionServiceImpl implements ChatSessionService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public User findById(String id) {
        if (id != null) {
            String value = null;
            if (id.startsWith(ChatConstant.USER_PREFIX)) {
                value = redisTemplate.boundValueOps(id).get();
            } else {
                value = redisTemplate.boundValueOps(ChatConstant.USER_PREFIX + id).get();
            }
            JSONObject object = JSONObject.parseObject(value);
            if (object != null) {
                return object.toJavaObject(User.class);
            }
        }
        return null;
    }

    @Override
    public void pushMessage(String fromId, String toId, String message) {
        Message entity = new Message();
        entity.setMessage(message);
        entity.setFrom(this.findById(fromId));
        entity.setTime(StringUtil.format(new Date()));
        if (toId != null) {
            //查询接收方信息
            entity.setTo(this.findById(toId));
            //单个用户推送
            push(entity, ChatConstant.CHAT_FROM_PREFIX + fromId + ChatConstant.CHAT_TO_PREFIX + toId);
        } else {
            //公共消息 -- 群组
            entity.setTo(null);
            push(entity, ChatConstant.CHAT_COMMON_PREFIX + fromId);
        }
    }

    /**
     * 推送消息
     *
     * @param entity Session value
     * @param key    Session key
     */
    private void push(Message entity, String key) {
        //这里按照 PREFIX_ID 格式，作为KEY储存消息记录
        //但一个用户可能推送很多消息，VALUE应该是数组
        List<Message> list = new ArrayList<>();
        String value = redisTemplate.boundValueOps(key).get();
        if (value == null) {
            //第一次推送消息
            list.add(entity);
        } else {
            //第n次推送消息
            list = Objects.requireNonNull(JSONObject.parseArray(value)).toJavaList(Message.class);
            list.add(entity);
        }
        redisTemplate.boundValueOps(key).set(JSONObject.toJSONString(list));
    }

    @Override
    public List<User> onlineList() {
        List<User> list = new ArrayList<>();
        Set<String> keys = redisTemplate.keys(ChatConstant.USER_PREFIX + ChatConstant.REDIS_MATCH_PREFIX);
        if (keys != null && keys.size() > 0) {
            keys.forEach(key -> {
                list.add(this.findById(key));
            });
        }
        return list;
    }

    @Override
    public List<Message> commonList() {
        List<Message> list = new ArrayList<>();
        Set<String> keys = redisTemplate.keys(ChatConstant.CHAT_COMMON_PREFIX + ChatConstant.REDIS_MATCH_PREFIX);
        if (keys != null && keys.size() > 0) {
            keys.forEach(key -> {
                String value = redisTemplate.boundValueOps(key).get();
                List<Message> messageList = Objects.requireNonNull(JSONObject.parseArray(value)).toJavaList(Message.class);
                list.addAll(messageList);
            });
        }
        ChatUtil.sort(list);
        return list;
    }

    @Override
    public List<Message> selfList(String fromId, String toId) {
        List<Message> list = new ArrayList<>();
        //A -> B
        String fromTo = redisTemplate.boundValueOps(ChatConstant.CHAT_FROM_PREFIX + fromId + ChatConstant.CHAT_TO_PREFIX + toId).get();
        //B -> A
        String toFrom = redisTemplate.boundValueOps(ChatConstant.CHAT_FROM_PREFIX + toId + ChatConstant.CHAT_TO_PREFIX + fromId).get();

        JSONArray fromToObject = JSONObject.parseArray(fromTo);
        JSONArray toFromObject = JSONObject.parseArray(toFrom);
        if (fromToObject != null) {
            list.addAll(fromToObject.toJavaList(Message.class));
        }
        if (toFromObject != null) {
            list.addAll(toFromObject.toJavaList(Message.class));
        }

        if (list.size() > 0) {
            ChatUtil.sort(list);
            return list;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void delete(String id) {
        if (id != null) {
            log.info("从Redis中删除此Key: " + id);
            redisTemplate.delete(ChatConstant.USER_PREFIX + id);
        }
    }
}

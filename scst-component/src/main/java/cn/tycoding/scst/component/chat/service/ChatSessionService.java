package cn.tycoding.scst.component.chat.service;

import cn.tycoding.scst.component.chat.entity.ChatMessage;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-06-12
 */
public interface ChatSessionService {

    /**
     * 保存会话消息
     *
     * @param id
     * @param message
     */
    void saveMessage(String id, String message);

    /**
     * 获取会话消息列表
     *
     * @return
     */
    List<ChatMessage> getMessageList();
}

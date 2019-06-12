package cn.tycoding.scst.component.chat.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 聊天消息储存
 *
 * @author tycoding
 * @date 2019-06-12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage implements Serializable {

    /**
     * 会话ID（用户ID）
     */
    private String id;

    /**
     * 推送消息的用户姓名
     */
    private String name;

    /**
     * 推送消息内容
     */
    private String message;

    /**
     * 接收消息方：
     *      如果群发的消息就设为null
     *      如果是给指定用户推送的消息，就设为接收消息方的ChatUser
     */
    private ChatUser to;

    /**
     * 推送消息的时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;
}

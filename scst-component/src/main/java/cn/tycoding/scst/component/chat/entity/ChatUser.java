package cn.tycoding.scst.component.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 聊天用户信息
 *
 * @author tycoding
 * @date 2019-06-12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatUser implements Serializable {

    /**
     * 用户ID
     */
    private String id;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户头像地址
     */
    private String avatar;
}

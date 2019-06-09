package cn.tycoding.scst.component.email.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tycoding
 * @date 2019-06-09
 */
@Data
public class MailProperties implements Serializable {

    /**
     * 发件邮箱
     */
    private String from;

    /**
     * 收件邮箱
     */
    private String to;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String text;

    /**
     * smtp服务器
     */
    private String host;

    /**
     * smtp服务器端口
     */
    private Integer port;

    /**
     * 授权码
     */
    private String password;

    /**
     * 自定义发件人名
     */
    private String sender;

}

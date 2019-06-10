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
}

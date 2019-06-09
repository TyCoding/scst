package cn.tycoding.scst.component.email.service;

/**
 * @author tycoding
 * @date 2019-06-09
 */
public interface MailService {

    /**
     * 发送邮件
     *
     * @param to      收件人邮箱
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    void send(String to, String subject, String content);
}

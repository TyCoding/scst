package cn.tycoding.scst.component.email.service;

import cn.tycoding.scst.component.email.entity.MailProperties;

/**
 * @author tycoding
 * @date 2019-06-09
 */
public interface MailService {

    /**
     * 发送邮件
     *
     * @param mailProperties 邮件参数
     */
    void send(MailProperties mailProperties);
}

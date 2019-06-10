package cn.tycoding.scst.component.email.service.impl;

import cn.tycoding.scst.component.email.entity.MailProperties;
import cn.tycoding.scst.component.email.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * @author tycoding
 * @date 2019-06-09
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void send(MailProperties mailProperties) {
        log.info("mail => {}", mailProperties);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(mailProperties.getTo());
            messageHelper.setFrom(username);
            messageHelper.setSubject(mailProperties.getSubject());
            messageHelper.setText(mailProperties.getText(), true);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

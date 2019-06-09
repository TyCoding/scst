package cn.tycoding.scst.component.email.service.impl;

import cn.tycoding.scst.component.email.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author tycoding
 * @date 2019-06-09
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void send(String to, String subject, String content) {
        JavaMailSenderImpl jms = new JavaMailSenderImpl();
        jms.setHost("smtp.sina.com");
        jms.setUsername("tycoding@sina.com");
        jms.setPassword("ty886.");
        jms.setDefaultEncoding("utf-8");
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        jms.setJavaMailProperties(properties);

//        SpringContextHolder.registerBean("javaMailSenderImpl", JavaMailSenderImpl.class);
//        SpringContextHolder.registerBean("javaMailSender", JavaMailSender.class);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(to);
            messageHelper.setFrom("tycoding@sina.com");
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

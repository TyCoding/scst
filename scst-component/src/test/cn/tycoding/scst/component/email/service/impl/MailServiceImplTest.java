package cn.tycoding.scst.component.email.service.impl;

import cn.tycoding.scst.component.email.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author tycoding
 * @date 2019-06-09
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MailServiceImplTest {

    @Autowired
    private MailService mailService;

    @Test
    public void send() {
        mailService.send("2557988481@qq.com", "From TyCoding", "<h1>this is Test Email</h1>");
    }
}

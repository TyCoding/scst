package cn.tycoding.scst.system.biz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tycoding
 * @date 2020/7/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordTest {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void passwordTest() {
        // $2a$10$I/SMezVZBVuMRfChtqxe7O0pdgmZff37QRPkTJnNQth3ONBrQ3IUK
        System.out.println(passwordEncoder.encode("123456"));
    }
}

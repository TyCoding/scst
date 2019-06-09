package cn.tycoding.scst.logging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 日志模块
 *
 * @author tycoding
 * @date 2019-06-08
 */
@MapperScan("cn.tycoding.scst.logging.mapper")
@SpringBootApplication
public class ScstLoggingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScstLoggingApplication.class, args);
    }
}

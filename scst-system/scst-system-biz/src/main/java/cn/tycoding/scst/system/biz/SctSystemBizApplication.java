package cn.tycoding.scst.system.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 系统权限管理模块
 *
 * @author tycoding
 * @date 2019-06-02
 */
@MapperScan("cn.tycoding.scst.system.biz.mapper")
@SpringBootApplication
public class SctSystemBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(SctSystemBizApplication.class, args);
    }
}

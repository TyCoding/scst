package cn.tycoding.scst.common.web.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author tycoding
 * @date 2020/7/13
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:scst-core.properties"}, encoding = "utf-8")
@ConfigurationProperties(prefix = "scst.core")
public class ScstProperties {

    private QiniuProperties qiniu = new QiniuProperties();

    private SwaggerProperties swagger = new SwaggerProperties();
}

package cn.tycoding.scst.common.security.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author tycoding
 * @date 2019-10-21
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:scst-core.properties"}, encoding = "utf-8")
@ConfigurationProperties(prefix = "scst.core")
public class ScstProperties {

    private QiniuProperties qiniu = new QiniuProperties();

    private SwaggerProperties swagger = new SwaggerProperties();
}

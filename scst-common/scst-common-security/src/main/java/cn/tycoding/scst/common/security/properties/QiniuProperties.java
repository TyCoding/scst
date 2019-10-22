package cn.tycoding.scst.common.security.properties;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tycoding
 * @date 2019-10-21
 */
@Data
public class QiniuProperties implements Serializable {

    /**
     * AccessKey
     */
    private String ak;

    /**
     * SecretKey
     */
    private String sk;

    /**
     * BucketName
     */
    private String bn;

    /**
     * 外链
     */
    private String url;
}

package cn.tycoding.scst.component.qiniu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author tycoding
 * @date 2019-06-12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Storage implements Serializable {

    /**
     * 对象KEY
     */
    private String key;

    /**
     * 对象名称
     */
    private String name;

    /**
     * 对象类型
     */
    private String type;

    /**
     * 对象大小
     */
    private long size;

    /**
     * 对象链接
     */
    private String url;
}

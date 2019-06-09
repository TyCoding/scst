package cn.tycoding.scst.logging.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * @author tycoding
 * @date 2019-06-08
 */
@Data
@Table(name = "sys_log")
public class SysLog implements Serializable {

    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 操作用户
     */
    private String username;

    /**
     * 日志描述
     */
    private String operation;

    /**
     * 操作耗时
     */
    private Long time;

    /**
     * 操作方法
     */
    private String method;

    /**
     * 方法参数
     */
    private String params;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 地理位置
     */
    private String location;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 用于搜索条件
     */
    @Transient
    private String timeField;
}

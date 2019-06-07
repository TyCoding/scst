package cn.tycoding.scst.system.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 *
 * @author tycoding
 * @date 2019-06-02
 */
@Builder
@Data
@Table(name = "sys_user")
@NoArgsConstructor
@AllArgsConstructor
public class SysUser implements Serializable {

    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 随机盐
     */
    private String salt;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 部门名称
     */
    @Transient
    private String deptName;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 电话
     */
    private Long phone;

    /**
     * 性别， 1男 2女
     */
    private String sex;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态：true可用、false锁定
     */
    private Boolean status;

    public void setUsername(String username) {
        this.username = username == null ? "" : username.trim();
    }
}

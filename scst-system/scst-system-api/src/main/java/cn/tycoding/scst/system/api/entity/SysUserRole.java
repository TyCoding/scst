package cn.tycoding.scst.system.api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 用户角色表
 *
 * @author tycoding
 * @date 2019-06-03
 */
@Data
@Table(name = "sys_user_role")
public class SysUserRole implements Serializable {

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Long roleId;
}

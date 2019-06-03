package cn.tycoding.scst.system.api.entity;

import lombok.Data;

import javax.persistence.Transient;
import java.util.List;

/**
 * @author tycoding
 * @date 2019-06-03
 */
@Data
public class SysUserWithRole extends SysUser {

    /**
     * 角色ID
     */
    @Transient
    private Long roleId;

    /**
     * 角色ID集合
     */
    @Transient
    private List<Long> roleIds;
}

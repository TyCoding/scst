package cn.tycoding.scst.system.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

/**
 * @author tycoding
 * @date 2020/7/13
 */
@Data
public class SysUserWithRole extends SysUser {

    /**
     * 角色ID
     */
    @TableField(exist = false)
    private Long roleId;

    /**
     * 角色ID集合
     */
    @TableField(exist = false)
    private List<Long> roleIds;
}

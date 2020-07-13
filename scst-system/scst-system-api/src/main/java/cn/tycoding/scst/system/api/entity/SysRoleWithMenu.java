package cn.tycoding.scst.system.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

/**
 * @author tycoding
 * @date 2020/7/13
 */
@Data
public class SysRoleWithMenu extends SysRole {

    /**
     * 菜单ID
     */
    @TableField(exist = false)
    private Long menuId;

    /**
     * 菜单ID集合
     */
    @TableField(exist = false)
    private List<Long> menuIds;
}

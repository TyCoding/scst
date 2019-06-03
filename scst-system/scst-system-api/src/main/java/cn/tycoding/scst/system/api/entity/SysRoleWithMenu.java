package cn.tycoding.scst.system.api.entity;

import lombok.Data;

import javax.persistence.Transient;
import java.util.List;

/**
 * @author tycoding
 * @date 2019-06-03
 */
@Data
public class SysRoleWithMenu extends SysRole {

    /**
     * 菜单ID
     */
    @Transient
    private Long menuId;

    /**
     * 菜单ID集合
     */
    @Transient
    private List<Long> menuIds;
}

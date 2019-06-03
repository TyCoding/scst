package cn.tycoding.scst.system.api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 角色菜单关联表
 *
 * @author tycoding
 * @date 2019-06-03
 */
@Data
@Table(name = "sys_role_menu")
public class SysRoleMenu implements Serializable {

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 菜单ID
     */
    @Column(name = "menu_id")
    private Long menuId;
}

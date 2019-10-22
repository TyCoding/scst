package cn.tycoding.scst.system.biz.service;

import cn.tycoding.scst.system.api.entity.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author tycoding
 * @date 2019-06-02
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 根据角色ID删除该角色的权限关联信息
     *
     * @param roleId
     */
    void deleteRoleMenusByRoleId(Long roleId);

    /**
     * 根据权限ID删除角色权限关联信息
     *
     * @param menuId
     */
    void deleteRoleMenusByMenuId(Long menuId);
}

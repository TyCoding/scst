package cn.tycoding.scst.system.biz.service;

import cn.tycoding.scst.common.service.BaseService;
import cn.tycoding.scst.system.api.entity.SysUserRole;

/**
 * @author tycoding
 * @date 2019-06-02
 */
public interface SysUserRoleService extends BaseService<SysUserRole> {

    /**
     * 根据角色ID删除该用户的角色关联信息
     *
     * @param roleId
     */
    void deleteUserRolesByRoleId(Long roleId);

    /**
     * 根据用户ID删除该用户的角色关联信息
     *
     * @param userId
     */
    void deleteUserRolesByUserId(Long userId);
}

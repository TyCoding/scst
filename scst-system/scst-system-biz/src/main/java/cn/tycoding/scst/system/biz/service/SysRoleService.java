package cn.tycoding.scst.system.biz.service;

import cn.tycoding.scst.common.service.BaseService;
import cn.tycoding.scst.system.api.entity.SysRole;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-06-02
 */
public interface SysRoleService extends BaseService<SysRole> {

    /**
     * 根据用户名查询用户的角色信息
     *
     * @param username
     * @return
     */
    List<SysRole> findUserRoles(String username);
}

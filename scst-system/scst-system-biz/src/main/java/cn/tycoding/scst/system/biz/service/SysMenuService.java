package cn.tycoding.scst.system.biz.service;

import cn.tycoding.scst.common.service.BaseService;
import cn.tycoding.scst.system.api.entity.SysMenu;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-06-02
 */
public interface SysMenuService extends BaseService<SysMenu> {

    /**
     * 根据用户名获取用户的权限信息
     *
     * @param username
     * @return
     */
    List<SysMenu> findUserPermissions(String username);
}

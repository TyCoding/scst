package cn.tycoding.scst.system.biz.service;

import cn.tycoding.scst.common.service.BaseService;
import cn.tycoding.scst.system.api.dto.Tree;
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

    /**
     * @param menu
     * @return
     */
    List<SysMenu> list(SysMenu menu);

    /**
     * 获取菜单权限Tree树
     *
     * @return
     */
    List<Tree<SysMenu>> tree();

    /**
     * 根据ID获取权限信息
     *
     * @param id
     * @return
     */
    SysMenu findById(Long id);

    /**
     * 添加权限
     *
     * @param menu
     */
    void add(SysMenu menu);

    /**
     * 删除权限
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 更新权限
     *
     * @param menu
     */
    void update(SysMenu menu);
}

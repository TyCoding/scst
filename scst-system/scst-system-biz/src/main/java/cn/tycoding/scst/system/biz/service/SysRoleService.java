package cn.tycoding.scst.system.biz.service;

import cn.tycoding.scst.common.service.BaseService;
import cn.tycoding.scst.system.api.entity.SysRole;
import cn.tycoding.scst.system.api.entity.SysRoleWithMenu;

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

    /**
     * 条件查询用户列表数据
     *
     * @param role
     * @return
     */
    List<SysRole> list(SysRole role);

    /**
     * 根据用户名查询器角色和权限信息
     *
     * @param id
     * @return
     */
    SysRoleWithMenu findById(Long id);

    /**
     * 添加角色，并添加该角色与权限的关联信息
     *
     * @param role
     */
    void add(SysRoleWithMenu role);

    /**
     * 更新角色，并更新（修改）该角色对应的权限关联信息
     *
     * @param role
     */
    void update(SysRoleWithMenu role);

    /**
     * 根据ID删除角色，并删除其关联的权限信息
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 校验当前名称是否存在
     *
     * @param name 名称
     * @param id   ID，如果是修改操作，name就一定存在
     * @return
     */
    boolean checkName(String name, String id);
}

package cn.tycoding.scst.system.biz.service;

import cn.tycoding.scst.common.service.BaseService;
import cn.tycoding.scst.system.api.dto.Tree;
import cn.tycoding.scst.system.api.entity.SysMenu;
import cn.tycoding.scst.system.api.entity.SysUser;
import cn.tycoding.scst.system.api.entity.SysUserWithRole;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-06-02
 */
public interface SysUserService extends BaseService<SysUser> {

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    SysUser findByName(String username);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    SysUserWithRole findById(Long id);

    /**
     * 根据用户名获取菜单信息
     *
     * @param username
     * @return
     */
    List<Tree<SysMenu>> getMenus(String username);

    /**
     * 查询用户集合数据
     *
     * @param user
     * @return
     */
    List<SysUser> list(SysUser user);

    /**
     * 新增
     *
     * @param user
     */
    void add(SysUserWithRole user);

    /**
     * 删除，并删除与该角色相关的权限信息
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 更新
     *
     * @param user
     */
    void update(SysUserWithRole user);

    /**
     * 修改密码
     *
     * @param password
     */
    void updatePassword(String password);

    /**
     * 校验当前用户名是否存在
     *
     * @param name
     * @param id
     * @return
     */
    boolean checkName(String name, Long id);
}

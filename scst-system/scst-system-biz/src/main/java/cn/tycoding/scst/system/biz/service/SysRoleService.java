package cn.tycoding.scst.system.biz.service;

import cn.tycoding.scst.common.web.utils.QueryPage;
import cn.tycoding.scst.system.api.entity.SysRole;
import cn.tycoding.scst.system.api.entity.SysRoleWithMenu;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author tycoding
 * @date 2020/7/13
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 根据用户名查询用户的角色信息
     */
    List<SysRole> findUserRoles(String username);

    /**
     * 分页、条件查询用户列表数据
     */
    IPage<SysRole> list(SysRole sysRole, QueryPage queryPage);

    /**
     * 条件查询用户列表数据
     */
    List<SysRole> list(SysRole role);

    /**
     * 根据用户名查询器角色和权限信息
     */
    SysRoleWithMenu findById(Long id);

    /**
     * 添加角色，并添加该角色与权限的关联信息
     */
    void add(SysRoleWithMenu role);

    /**
     * 更新角色，并更新（修改）该角色对应的权限关联信息
     */
    void update(SysRoleWithMenu role);

    /**
     * 根据ID删除角色，并删除其关联的权限信息
     */
    void delete(Long id);

    /**
     * 校验当前名称是否存在
     */
    boolean checkName(String name, String id);
}

package cn.tycoding.scst.system.biz.service;

import cn.tycoding.scst.common.web.utils.QueryPage;
import cn.tycoding.scst.system.api.dto.Tree;
import cn.tycoding.scst.system.api.dto.UserInfo;
import cn.tycoding.scst.system.api.entity.SysMenu;
import cn.tycoding.scst.system.api.entity.SysUser;
import cn.tycoding.scst.system.api.entity.SysUserWithRole;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author tycoding
 * @date 2020/7/13
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 获取用户基本信息、角色信息、权限信息
     */
    UserInfo getUserInfo(SysUser user);

    /**
     * 根据用户名查询
     */
    SysUser findByName(String username);

    /**
     * 根据ID查询
     */
    SysUserWithRole findById(Long id);

    /**
     * 根据用户名获取菜单信息
     */
    List<Tree<SysMenu>> getMenus(String username);

    /**
     * 查询用户集合数据
     */
    IPage<SysUser> list(SysUser user, QueryPage queryPage);

    /**
     * 新增
     */
    void add(SysUserWithRole user);

    /**
     * 删除，并删除与该角色相关的权限信息
     */
    void delete(Long id);

    /**
     * 更新
     */
    void update(SysUserWithRole user);

    /**
     * 修改密码
     */
    void updatePassword(String password);

    /**
     * 校验当前名称是否存在
     */
    boolean checkName(String name, String id);
}

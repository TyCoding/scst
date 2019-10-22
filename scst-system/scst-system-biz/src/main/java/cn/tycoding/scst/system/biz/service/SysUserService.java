package cn.tycoding.scst.system.biz.service;

import cn.tycoding.scst.common.core.utils.QueryPage;
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
 * @date 2019-06-02
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 获取用户基本信息、角色信息、权限信息
     *
     * @param user
     * @return
     */
    UserInfo getUserInfo(SysUser user);

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
    IPage<SysUser> list(SysUser user, QueryPage queryPage);

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
     * 校验当前名称是否存在
     *
     * @param name 名称
     * @param id   ID，如果是修改操作，name就一定存在
     * @return
     */
    boolean checkName(String name, String id);
}

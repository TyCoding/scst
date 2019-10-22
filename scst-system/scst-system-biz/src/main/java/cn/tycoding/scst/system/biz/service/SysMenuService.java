package cn.tycoding.scst.system.biz.service;

import cn.tycoding.scst.common.core.utils.QueryPage;
import cn.tycoding.scst.system.api.dto.MenuTree;
import cn.tycoding.scst.system.api.dto.Tree;
import cn.tycoding.scst.system.api.entity.SysMenu;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-06-02
 */
public interface SysMenuService extends IService<SysMenu> {

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
    IPage<SysMenu> list(SysMenu menu, QueryPage queryPage);

    /**
     * 获取菜单Tree树
     *
     * @return
     */
    List<Tree<SysMenu>> tree();

    /**
     * 用于构建左侧权限菜单
     *
     * @return
     */
    List<MenuTree<SysMenu>> build();

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

    /**
     * 校验当前名称是否存在
     *
     * @param name 名称
     * @param id   ID，如果是修改操作，name就一定存在
     * @return
     */
    boolean checkName(String name, String id);
}

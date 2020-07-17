package cn.tycoding.scst.system.biz.service;

import cn.tycoding.scst.common.web.utils.QueryPage;
import cn.tycoding.scst.system.api.dto.MenuTree;
import cn.tycoding.scst.system.api.dto.Tree;
import cn.tycoding.scst.system.api.entity.SysMenu;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author tycoding
 * @date 2020/7/13
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 根据用户名获取用户的权限信息
     */
    List<SysMenu> findUserPermissions(String username);

    /**
     * 分页、条件查询
     */
    IPage<SysMenu> list(SysMenu menu, QueryPage queryPage);

    /**
     * 条件查询
     */
    List<SysMenu> list(SysMenu menu);

    /**
     * 获取菜单Tree树
     */
    List<Tree<SysMenu>> tree();

    /**
     * 构建左侧权限菜单
     */
    List<MenuTree<SysMenu>> build();

    /**
     * 添加菜单
     */
    void add(SysMenu menu);

    /**
     * 删除菜单
     */
    void delete(Long id);

    /**
     * 更新菜单
     */
    void update(SysMenu menu);

    /**
     * 校验当前名称是否存在
     */
    boolean checkName(String name, String id);
}

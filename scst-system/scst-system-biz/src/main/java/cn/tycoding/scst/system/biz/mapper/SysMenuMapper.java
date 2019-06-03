package cn.tycoding.scst.system.biz.mapper;

import cn.tycoding.scst.common.repository.MapperRepository;
import cn.tycoding.scst.system.api.entity.SysMenu;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-06-02
 */
public interface SysMenuMapper extends MapperRepository<SysMenu> {

    /**
     * 根据用户名查询此用户可访问的菜单列表
     *
     * @param username
     * @return
     */
    List<SysMenu> findUserMenus(String username);
}

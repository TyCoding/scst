package cn.tycoding.scst.system.biz.service.impl;

import cn.tycoding.scst.common.service.impl.BaseServiceImpl;
import cn.tycoding.scst.system.api.dto.Tree;
import cn.tycoding.scst.system.api.entity.SysMenu;
import cn.tycoding.scst.system.api.utils.TreeUtils;
import cn.tycoding.scst.system.biz.mapper.SysMenuMapper;
import cn.tycoding.scst.system.biz.service.SysMenuService;
import cn.tycoding.scst.system.biz.service.SysRoleMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author tycoding
 * @date 2019-06-02
 */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public List<SysMenu> findUserPermissions(String username) {
        return sysMenuMapper.findUserMenus(username);
    }

    @Override
    public List<SysMenu> list(SysMenu menu) {
        Example example = new Example(SysMenu.class);
        if (StringUtils.isNoneBlank(menu.getName())) {
            example.createCriteria().andCondition("name=", menu.getName());
        }
        if (StringUtils.isNoneBlank(menu.getType())) {
            example.createCriteria().andCondition("type=", menu.getType());
        }
        example.setOrderByClause("id");
        return this.selectByExample(example);
    }

    @Override
    public List<Tree<SysMenu>> tree() {
        List<Tree<SysMenu>> treeList = new ArrayList<>();
        buildMenuTree(treeList, list(new SysMenu()));
        return TreeUtils.build(treeList);
    }

    private void buildMenuTree(List<Tree<SysMenu>> treeList, List<SysMenu> menus) {
        menus.forEach(menu -> {
            Tree<SysMenu> tree = new Tree<>();
            tree.setId(menu.getId());
            tree.setName(menu.getName());
            tree.setUrl(menu.getUrl());
            tree.setParentId(menu.getParentId());
            treeList.add(tree);
        });
    }

    @Override
    public SysMenu findById(Long id) {
        return this.selectByKey(id);
    }

    @Override
    @Transactional
    public void add(SysMenu menu) {
        menu.setCreateTime(new Date());
        if (menu.getParentId() == null) {
            menu.setParentId(0L);
        }
        if (SysMenu.TYPE_BUTTON.equals(menu.getType())) {
            menu.setUrl(null);
            menu.setIcon(null);
        }
        this.updateNotNull(menu);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        sysMenuMapper.deleteByExample(id);
        sysRoleMenuService.deleteRoleMenusByMenuId(id);
        sysMenuMapper.changeTopNode(id);
    }

    @Override
    @Transactional
    public void update(SysMenu menu) {
        if (menu.getParentId() != null) {
            menu.setParentId(0L);
        }
        if (SysMenu.TYPE_BUTTON.equals(menu.getType())) {
            menu.setIcon(null);
            menu.setUrl(null);
        }
        this.updateNotNull(menu);
    }
}

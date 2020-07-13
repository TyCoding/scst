package cn.tycoding.scst.system.biz.service.impl;

import cn.tycoding.scst.common.web.utils.QueryPage;
import cn.tycoding.scst.system.api.dto.MenuMeta;
import cn.tycoding.scst.system.api.dto.MenuTree;
import cn.tycoding.scst.system.api.dto.Tree;
import cn.tycoding.scst.system.api.entity.SysMenu;
import cn.tycoding.scst.system.api.utils.TreeUtils;
import cn.tycoding.scst.system.biz.mapper.SysMenuMapper;
import cn.tycoding.scst.system.biz.service.SysMenuService;
import cn.tycoding.scst.system.biz.service.SysRoleMenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tycoding
 * @date 2020/7/13
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public List<SysMenu> findUserPermissions(String username) {
        return sysMenuMapper.findUserMenus(username);
    }

    @Override
    public IPage<SysMenu> list(SysMenu menu, QueryPage queryPage) {
        IPage<SysMenu> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(menu.getName()), SysMenu::getName, menu.getName());
        queryWrapper.like(StringUtils.isNotBlank(menu.getType()), SysMenu::getType, menu.getType());
        return sysMenuMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<Tree<SysMenu>> tree() {
        List<Tree<SysMenu>> treeList = new ArrayList<>();
        List<SysMenu> menuList = sysMenuMapper.selectList(new LambdaQueryWrapper<>());
        if (menuList.size() > 0) {
            menuList.forEach(menu -> {
                Tree<SysMenu> tree = new Tree<>();
                tree.setId(menu.getId());
                tree.setName(menu.getName());
                tree.setPath(menu.getPath());
                tree.setIcon(menu.getIcon());
                tree.setType(menu.getType());
                tree.setPerms(menu.getPerms());
                tree.setComponent(menu.getComponent());
                tree.setParentId(menu.getParentId());
                treeList.add(tree);
            });
        }
        return TreeUtils.build(treeList);
    }

    @Override
    public List<MenuTree<SysMenu>> build() {
        List<MenuTree<SysMenu>> treeList = new ArrayList<>();
        List<SysMenu> menuList = sysMenuMapper.selectList(new LambdaQueryWrapper<>());
        if (menuList.size() > 0) {
            menuList.forEach(menu -> {
                if (menu.getType().equals(SysMenu.TYPE_MENU)) {
                    MenuTree<SysMenu> tree = new MenuTree<>();
                    tree.setId(menu.getId());
                    tree.setName(menu.getName());
                    tree.setPath(menu.getPath());
                    tree.setMeta(new MenuMeta(menu.getName(), menu.getIcon()));
                    tree.setComponent(menu.getComponent());
                    tree.setHidden(menu.getHidden());
                    tree.setParentId(menu.getParentId());
                    treeList.add(tree);
                }
            });
        }
        return this.build(treeList);
    }

    private List<MenuTree<SysMenu>> build(List<MenuTree<SysMenu>> nodes) {
        if (nodes == null) {
            return null;
        }
        List<MenuTree<SysMenu>> tree = new ArrayList<>();
        nodes.forEach(node -> {
            Long pid = node.getParentId();
            if (pid == null || pid.equals(0L)) {
                node.setComponent("Layout");
                node.setAlwaysShow(true);
                tree.add(node);
                return;
            }
            for (MenuTree<SysMenu> c : nodes) {
                Long id = c.getId();
                if (id != null && id.equals(pid)) {
                    c.getChildren().add(node);
                    return;
                }
            }
        });
        return tree;
    }

    @Override
    @Transactional
    public void add(SysMenu menu) {
        if (menu.getParentId() == null) {
            menu.setParentId(0L);
        }
        // TODO
        if (SysMenu.TYPE_BUTTON.equals(menu.getType())) {
            menu.setPath(null);
            menu.setIcon(null);
        }
        this.save(menu);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        sysMenuMapper.deleteById(id);
        sysRoleMenuService.deleteRoleMenusByMenuId(id);
        sysMenuMapper.changeTopNode(id);
    }

    @Override
    @Transactional
    public void update(SysMenu menu) {
        if (menu.getParentId() == null) {
            menu.setParentId(0L);
        }
        // TODO
        if (SysMenu.TYPE_BUTTON.equals(menu.getType())) {
            menu.setIcon(null);
        }
        this.updateById(menu);
    }

    @Override
    public boolean checkName(String name, String id) {
        if (StringUtils.isBlank(name)) {
            return false;
        }
        LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(id)) {
            queryWrapper.eq(SysMenu::getName, name);
            queryWrapper.ne(SysMenu::getId, id);
        } else {
            queryWrapper.eq(SysMenu::getName, name);
        }
        return sysMenuMapper.selectList(queryWrapper).size() <= 0;
    }
}

package cn.tycoding.scst.system.biz.service.impl;

import cn.tycoding.scst.common.web.utils.QueryPage;
import cn.tycoding.scst.system.api.dto.Tree;
import cn.tycoding.scst.system.api.dto.UserInfo;
import cn.tycoding.scst.system.api.entity.*;
import cn.tycoding.scst.system.api.utils.TreeUtils;
import cn.tycoding.scst.system.biz.mapper.SysMenuMapper;
import cn.tycoding.scst.system.biz.mapper.SysUserMapper;
import cn.tycoding.scst.system.biz.mapper.SysUserRoleMapper;
import cn.tycoding.scst.system.biz.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author tycoding
 * @date 2020/7/13
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysDeptService sysDeptService;

    @Override
    public UserInfo getUserInfo(SysUser user) {
        UserInfo userInfo = new UserInfo();

        //获取用户角色列表
        List<SysRole> roleList = sysRoleService.findUserRoles(user.getUsername());
        Set<String> roleSet = roleList.stream().map(SysRole::getName).collect(Collectors.toSet());

        //获取用户权限列表
        List<SysMenu> menuList = sysMenuService.findUserPermissions(user.getUsername());
        Set<String> menuSet = menuList
                .stream()
                .filter(perm -> (perm.getPerms() != null && !perm.getPerms().equals("")))
                .map(SysMenu::getPerms)
                .collect(Collectors.toSet());

        //获取用户部门列表
//        SysDept dept = sysDeptService.findById(user.getDeptId());

        userInfo.setSysUser(user);
        userInfo.setRoles(roleSet);
        userInfo.setPermissions(menuSet);
        return userInfo;
    }

    @Override
    public SysUser findByName(String username) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername, username);
        List<SysUser> list = sysUserMapper.selectList(queryWrapper);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public SysUserWithRole findById(Long id) {
        List<SysUserWithRole> list = sysUserMapper.findById(id);
        if (list.isEmpty()) {
            return null;
        }
        List<Long> roleIds = list.stream().map(SysUserWithRole::getRoleId).collect(Collectors.toList());
        SysUserWithRole sysUserWithRole = list.get(0);
        sysUserWithRole.setRoleIds(roleIds);
        return sysUserWithRole;
    }

    @Override
    public List<Tree<SysMenu>> getMenus(String username) {
        List<SysMenu> menus = sysMenuMapper.findUserMenus(username);
        List<Tree<SysMenu>> treeList = new ArrayList<>();
        menus.forEach(menu -> {
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
        return TreeUtils.build(treeList);
    }

    @Override
    public IPage<SysUser> list(SysUser user, QueryPage queryPage) {
        Page<SysUser> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        return sysUserMapper.list(page, user);
    }

    @Override
    @Transactional
    public void add(SysUserWithRole user) {
        user.setCreateTime(new Date());
        user.setModifyTime(new Date());
        user.setSex("0");
        //TODO
        user.setSalt("xxxxx");
        this.save(user);
        saveUserRole(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        sysUserMapper.deleteById(id);
        sysUserRoleService.deleteUserRolesByUserId(id);
    }

    private void saveUserRole(SysUserWithRole user) {
        user.getRoleIds().forEach(roleId -> {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleId);
            sysUserRoleMapper.insert(userRole);
        });
    }

    @Override
    @Transactional
    public void update(SysUserWithRole user) {
        user.setPassword(null);
        user.setModifyTime(new Date());
        this.updateById(user);

        //删除与此用户关联的角色的关联信息，并建议建立用户角色新的关联
        sysUserRoleService.deleteUserRolesByUserId(user.getId());
        saveUserRole(user);
    }

    @Override
    @Transactional
    public void updatePassword(String password) {
        //TODO
    }

    @Override
    public boolean checkName(String name, String id) {
        if (StringUtils.isBlank(name)) {
            return false;
        }
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(id)) {
            queryWrapper.eq(SysUser::getUsername, name);
            queryWrapper.ne(SysUser::getId, id);
        } else {
            queryWrapper.eq(SysUser::getUsername, name);
        }
        return sysUserMapper.selectList(queryWrapper).size() <= 0;
    }


}

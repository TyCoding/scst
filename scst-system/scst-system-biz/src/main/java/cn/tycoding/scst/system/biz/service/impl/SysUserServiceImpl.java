package cn.tycoding.scst.system.biz.service.impl;

import cn.tycoding.scst.common.service.impl.BaseServiceImpl;
import cn.tycoding.scst.system.api.dto.Tree;
import cn.tycoding.scst.system.api.entity.SysMenu;
import cn.tycoding.scst.system.api.entity.SysUser;
import cn.tycoding.scst.system.api.entity.SysUserRole;
import cn.tycoding.scst.system.api.entity.SysUserWithRole;
import cn.tycoding.scst.system.api.utils.TreeUtils;
import cn.tycoding.scst.system.biz.mapper.SysMenuMapper;
import cn.tycoding.scst.system.biz.mapper.SysUserMapper;
import cn.tycoding.scst.system.biz.mapper.SysUserRoleMapper;
import cn.tycoding.scst.system.biz.service.SysUserRoleService;
import cn.tycoding.scst.system.biz.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tycoding
 * @date 2019-06-02
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public SysUser findByName(String username) {
        Example example = new Example(SysUser.class);
        if (StringUtils.isNoneBlank(username)) {
            example.createCriteria().andCondition("username=", username);
        }
        List<SysUser> list = this.selectByExample(example);
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
        menus.forEach(sysMenu -> {
            Tree<SysMenu> tree = new Tree<>();
            tree.setId(sysMenu.getId());
            tree.setParentId(sysMenu.getParentId());
            tree.setName(sysMenu.getName());
            tree.setIcon(sysMenu.getIcon());
            treeList.add(tree);
        });
        return TreeUtils.build(treeList);
    }

    @Override
    public List<SysUser> list(SysUser user) {
        try {
            return sysUserMapper.list(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional
    public void add(SysUserWithRole user) {
        user.setCreateTime(new Date());
        this.save(user);
        saveUserRole(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        sysUserMapper.deleteByPrimaryKey(id);
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
        this.updateNotNull(user);

        //删除与此用户关联的角色的关联信息
        Example example = new Example(SysUserRole.class);
        example.createCriteria().andCondition("user_id", user.getId());
        sysUserRoleMapper.deleteByExample(example);
        saveUserRole(user);
    }

    @Override
    @Transactional
    public void updatePassword(String password) {

    }

    @Override
    public boolean checkName(String name, Long id) {
        return false;
    }


}

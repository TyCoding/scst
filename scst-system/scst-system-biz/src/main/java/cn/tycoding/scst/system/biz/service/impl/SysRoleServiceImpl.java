package cn.tycoding.scst.system.biz.service.impl;

import cn.tycoding.scst.common.service.impl.BaseServiceImpl;
import cn.tycoding.scst.system.api.entity.SysRole;
import cn.tycoding.scst.system.api.entity.SysRoleMenu;
import cn.tycoding.scst.system.api.entity.SysRoleWithMenu;
import cn.tycoding.scst.system.biz.mapper.SysRoleMapper;
import cn.tycoding.scst.system.biz.mapper.SysRoleMenuMapper;
import cn.tycoding.scst.system.biz.service.SysRoleMenuService;
import cn.tycoding.scst.system.biz.service.SysRoleService;
import cn.tycoding.scst.system.biz.service.SysUserRoleService;
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
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public List<SysRole> findUserRoles(String username) {
        return sysRoleMapper.findUserRoles(username);
    }

    @Override
    public List<SysRole> list(SysRole role) {
        try {
            Example example = new Example(SysRole.class);
            if (StringUtils.isNotBlank(role.getName())) {
                example.createCriteria().andLike("name", "%" + role.getName() + "%");
            }
            example.setOrderByClause("create_time");
            return this.selectByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public SysRoleWithMenu findById(Long id) {
        List<SysRoleWithMenu> list = sysRoleMapper.findById(id);
        List<Long> menuIds = list.stream().map(SysRoleWithMenu::getMenuId).collect(Collectors.toList());
        if (list.isEmpty()) {
            return null;
        }
        SysRoleWithMenu sysRoleWithMenu = list.get(0);
        sysRoleWithMenu.setMenuIds(menuIds);
        return sysRoleWithMenu;
    }

    @Override
    @Transactional
    public void add(SysRoleWithMenu role) {
        role.setCreateTime(new Date());
        this.save(role);
        saveRoleMenu(role);
    }

    private void saveRoleMenu(SysRoleWithMenu role) {
        if (role.getMenuIds() != null && role.getMenuIds().get(0) != null) {
            role.getMenuIds().forEach(menuId -> {
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setMenuId(menuId);
                roleMenu.setRoleId(role.getId());
                sysRoleMenuMapper.insert(roleMenu);
            });
        }
    }

    @Override
    @Transactional
    public void update(SysRoleWithMenu role) {
        this.updateNotNull(role);
        Example example = new Example(SysRoleMenu.class);
        example.createCriteria().andCondition("role_id=", role.getId());
        sysRoleMenuMapper.deleteByExample(example);
        this.saveRoleMenu(role);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        sysRoleMapper.deleteByPrimaryKey(id);
        sysRoleMenuService.deleteRoleMenusByRoleId(id);
        sysUserRoleService.deleteUserRolesByRoleId(id);
    }

    @Override
    public boolean checkName(String name, String id) {
        if (StringUtils.isBlank(name)) {
            return false;
        }
        Example example = new Example(SysRole.class);
        if (StringUtils.isNotBlank(id)) {
            example.createCriteria().andCondition("lower(name)=", name.toLowerCase()).andNotEqualTo("id", id);
        } else {
            example.createCriteria().andCondition("lower(name)=", name.toLowerCase());
        }
        return this.selectByExample(example).size() > 0 ? false : true;
    }
}

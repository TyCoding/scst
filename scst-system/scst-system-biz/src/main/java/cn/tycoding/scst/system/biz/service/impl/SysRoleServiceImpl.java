package cn.tycoding.scst.system.biz.service.impl;

import cn.tycoding.scst.common.web.utils.QueryPage;
import cn.tycoding.scst.system.api.entity.SysRole;
import cn.tycoding.scst.system.api.entity.SysRoleMenu;
import cn.tycoding.scst.system.api.dto.RoleWithMenu;
import cn.tycoding.scst.system.biz.mapper.SysRoleMapper;
import cn.tycoding.scst.system.biz.mapper.SysRoleMenuMapper;
import cn.tycoding.scst.system.biz.service.SysRoleMenuService;
import cn.tycoding.scst.system.biz.service.SysRoleService;
import cn.tycoding.scst.system.biz.service.SysUserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tycoding
 * @date 2020/7/13
 */
@Service
@AllArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final SysRoleMenuMapper sysRoleMenuMapper;

    private final SysRoleMenuService sysRoleMenuService;

    private final SysUserRoleService sysUserRoleService;

    @Override
    public List<SysRole> findRolesByUserId(Long id) {
        return baseMapper.findRolesByUserId(id);
    }

    @Override
    public IPage<SysRole> list(SysRole sysRole, QueryPage queryPage) {
        IPage<SysRole> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(sysRole.getName()), SysRole::getName, sysRole.getName());
        queryWrapper.orderByDesc(SysRole::getCreateTime);
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<SysRole> list(SysRole sysRole) {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(sysRole.getName()), SysRole::getName, sysRole.getName());
        queryWrapper.orderByDesc(SysRole::getCreateTime);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public RoleWithMenu findById(Long id) {
        List<RoleWithMenu> list = baseMapper.findById(id);
        List<Long> menuIds = list.stream().map(RoleWithMenu::getMenuId).collect(Collectors.toList());
        if (list.isEmpty()) {
            return null;
        }
        RoleWithMenu roleWithMenu = list.get(0);
        roleWithMenu.setMenuIds(menuIds);
        return roleWithMenu;
    }

    @Override
    public boolean checkName(SysRole sysRole) {
        if (StringUtils.isBlank(sysRole.getName())) {
            return false;
        }
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        if (sysRole.getId() != null && sysRole.getId() != 0) {
            queryWrapper.eq(SysRole::getName, sysRole.getName());
            queryWrapper.ne(SysRole::getId, sysRole.getId());
        } else {
            queryWrapper.eq(SysRole::getName, sysRole.getName());
        }
        return baseMapper.selectList(queryWrapper).size() <= 0;
    }

    @Override
    @Transactional
    public void add(RoleWithMenu sysRole) {
        sysRole.setCreateTime(new Date());
        this.save(sysRole);
        addRoleMenu(sysRole);
    }

    private void addRoleMenu(RoleWithMenu sysRole) {
        if (sysRole.getMenuIds() != null && sysRole.getMenuIds().get(0) != null) {
            sysRole.getMenuIds().forEach(menuId -> {
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setMenuId(menuId);
                roleMenu.setRoleId(sysRole.getId());
                sysRoleMenuMapper.insert(roleMenu);
            });
        }
    }

    @Override
    @Transactional
    public void update(RoleWithMenu sysRole) {
        this.updateById(sysRole);
        sysRoleMenuService.deleteRoleMenusByRoleId(sysRole.getId());
        addRoleMenu(sysRole);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        baseMapper.deleteById(id);
        sysRoleMenuService.deleteRoleMenusByRoleId(id);
        sysUserRoleService.deleteUserRolesByRoleId(id);
    }
}

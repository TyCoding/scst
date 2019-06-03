package cn.tycoding.scst.system.biz.service.impl;

import cn.tycoding.scst.common.service.impl.BaseServiceImpl;
import cn.tycoding.scst.system.api.entity.SysRole;
import cn.tycoding.scst.system.api.entity.SysRoleWithMenu;
import cn.tycoding.scst.system.biz.mapper.SysRoleMapper;
import cn.tycoding.scst.system.biz.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-06-02
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> findUserRoles(String username) {
        return sysRoleMapper.findUserRoles(username);
    }

    @Override
    public List<SysRole> list(SysRole role) {
        return null;
    }

    @Override
    public List<SysRoleWithMenu> findById(Long id) {
        return null;
    }

    @Override
    public void add(SysRoleWithMenu role) {

    }

    @Override
    public void update(SysRoleWithMenu role) {

    }
}

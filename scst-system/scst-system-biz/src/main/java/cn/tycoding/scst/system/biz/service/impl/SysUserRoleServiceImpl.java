package cn.tycoding.scst.system.biz.service.impl;

import cn.tycoding.scst.common.service.impl.BaseServiceImpl;
import cn.tycoding.scst.system.api.entity.SysUserRole;
import cn.tycoding.scst.system.biz.mapper.SysUserRoleMapper;
import cn.tycoding.scst.system.biz.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @author tycoding
 * @date 2019-06-02
 */
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole> implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    @Transactional
    public void deleteUserRolesByRoleId(Long roleId) {
        Example example = new Example(SysUserRole.class);
        example.createCriteria().andCondition("role_id", roleId);
        sysUserRoleMapper.deleteByExample(example);
    }

    @Override
    @Transactional
    public void deleteUserRolesByUserId(Long userId) {
        Example example = new Example(SysUserRole.class);
        example.createCriteria().andCondition("user_id", userId);
        sysUserRoleMapper.deleteByExample(example);
    }
}

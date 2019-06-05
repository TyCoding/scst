package cn.tycoding.scst.system.biz.service.impl;

import cn.tycoding.scst.common.service.impl.BaseServiceImpl;
import cn.tycoding.scst.system.api.entity.SysRoleMenu;
import cn.tycoding.scst.system.biz.mapper.SysRoleMenuMapper;
import cn.tycoding.scst.system.biz.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @author tycoding
 * @date 2019-06-02
 */
@Service
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenu> implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    @Transactional
    public void deleteRoleMenusByRoleId(Long roleId) {
        Example example = new Example(SysRoleMenu.class);
        example.createCriteria().andCondition("role_id=", roleId);
        sysRoleMenuMapper.deleteByExample(example);
    }

    @Override
    @Transactional
    public void deleteRoleMenusByMenuId(Long menuId) {
        Example example = new Example(SysRoleMenu.class);
        example.createCriteria().andCondition("menu_id=", menuId);
        sysRoleMenuMapper.deleteByExample(example);
    }
}

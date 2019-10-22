package cn.tycoding.scst.system.biz.mapper;

import cn.tycoding.scst.system.api.entity.SysRole;
import cn.tycoding.scst.system.api.entity.SysRoleWithMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-06-02
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> findUserRoles(String username);

    List<SysRoleWithMenu> findById(Long id);
}

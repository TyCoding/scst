package cn.tycoding.scst.system.biz.mapper;

import cn.tycoding.scst.system.api.entity.SysRole;
import cn.tycoding.scst.system.api.dto.RoleWithMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author tycoding
 * @date 2020/7/13
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> findRolesByUserId(Long id);

    List<RoleWithMenu> findById(Long id);
}

package cn.tycoding.scst.system.biz.mapper;

import cn.tycoding.scst.common.repository.MapperRepository;
import cn.tycoding.scst.system.api.entity.SysRole;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-06-02
 */
public interface SysRoleMapper extends MapperRepository<SysRole> {

    List<SysRole> findUserRoles(String username);
}

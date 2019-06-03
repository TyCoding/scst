package cn.tycoding.scst.system.biz.mapper;

import cn.tycoding.scst.common.repository.MapperRepository;
import cn.tycoding.scst.system.api.entity.SysUser;
import cn.tycoding.scst.system.api.entity.SysUserWithRole;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-06-02
 */
public interface SysUserMapper extends MapperRepository<SysUser> {

    List<SysUserWithRole> findById(Long id);

    List<SysUser> list(SysUser user);
}

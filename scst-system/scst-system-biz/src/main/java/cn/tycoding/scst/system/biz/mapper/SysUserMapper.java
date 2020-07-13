package cn.tycoding.scst.system.biz.mapper;

import cn.tycoding.scst.system.api.entity.SysUser;
import cn.tycoding.scst.system.api.entity.SysUserWithRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tycoding
 * @date 2020/7/13
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUserWithRole> findById(Long id);

    IPage<SysUser> list(Page page, @Param("user") SysUser user);
}

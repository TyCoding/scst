package cn.tycoding.scst.system.api.dto;

import cn.tycoding.scst.system.api.entity.SysUser;
import lombok.Data;

import java.util.Set;

/**
 * 封装用户基本信息、角色信息、权限信息
 *
 * @author tycoding
 * @date 2020/7/13
 */
@Data
public class UserInfo {

    /**
     * 用户基本信息
     */
    private SysUser sysUser;

    /**
     * 用户角色列表
     */
    Set<String> roles;

    /**
     * 用户权限列表
     */
    Set<String> permissions;
}

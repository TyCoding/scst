package cn.tycoding.scst.common.security.service;

import cn.tycoding.scst.common.core.constant.CommonConstants;
import cn.tycoding.scst.common.core.utils.R;
import cn.tycoding.scst.system.api.dto.UserInfo;
import cn.tycoding.scst.system.api.entity.SysUser;
import cn.tycoding.scst.system.api.feign.RemoteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author tycoding
 * @date 2020/7/13
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RemoteUserService remoteUserService;

    /**
     * 加载用户信息，在这里可做登录用户的权限、角色判断
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername >>>>>>>>>>>>> username: {}", username);
        R<UserInfo> info = remoteUserService.info(username);
        log.info("loadUserByUsername success >>>>>>>>>>>>>> info: {}", info);
        return getUserDetails(info);
    }

    private UserDetails getUserDetails(R<UserInfo> info) {
        if (info == null || info.getData() == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        UserInfo userInfo = info.getData();
        Set<String> authSet = new HashSet<>();

        Set<String> roles = userInfo.getRoles();
        if (roles != null || roles.size() > 0) {
            roles.forEach(role -> authSet.add(CommonConstants.ROLE_PREFIX + role));
        }
        Set<String> permissions = userInfo.getPermissions();
        if (permissions != null || permissions.size() > 0) {
            authSet.addAll(permissions);
        }
        SysUser user = userInfo.getSysUser();

//        String perms = permissions.stream().map(String::trim).collect(Collectors.joining(","));

//        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(perms);
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ADMIN");

        return new ScstUser(user.getId(), user.getUsername(), user.getPassword(),
                true, true, true, true,
                authorities);
    }
}

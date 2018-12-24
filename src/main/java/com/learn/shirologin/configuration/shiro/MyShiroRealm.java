package com.learn.shirologin.configuration.shiro;

import com.learn.shirologin.pojo.User;
import com.learn.shirologin.service.UserService;
import com.learn.shirologin.util.Contants;
import com.learn.shirologin.util.UseMD5;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        User user = (User) session.getAttribute(Contants.SESSION_USER);
        List<Integer> userRoleIds = userService.getUserRoleIds(user.getId());   //查询当前用户的所有角色
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if (userRoleIds != null && userRoleIds.size() > 0) {
            for (Integer roleId : userRoleIds) {
                simpleAuthorizationInfo.addRole(roleId.toString());
            }
            return simpleAuthorizationInfo;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        String name = (String) authcToken.getPrincipal();
        String password = new String((char[]) authcToken.getCredentials());
        User user = userService.findByName(name);
        if (user != null && (user.getUserpassword()).equals(UseMD5.createKey(password))) {
            return new SimpleAuthenticationInfo(name, password, ByteSource.Util.bytes(Contants.SALT), getName());
        }
        return null;
    }
}

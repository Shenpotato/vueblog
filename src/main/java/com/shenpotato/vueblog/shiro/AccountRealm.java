package com.shenpotato.vueblog.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.shenpotato.vueblog.entity.User;
import com.shenpotato.vueblog.service.UserService;
import com.shenpotato.vueblog.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
* supports：为了让realm支持jwt的凭证校验
* doGetAuthorizationInfo：权限校验
* doGetAuthenticationInfo：登录认证校验
* */
@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;          //tools for generate and validate jwt

    @Autowired
    UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /*
    * 登陆认证
    *
    * 通过jwt获取用户信息，判断用户状态。最后封装成SimpleAuthenticationInfo返回给shiro
    * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        JwtToken jwtToken = (JwtToken) authenticationToken;

        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();

        User user = userService.getById(Long.valueOf(userId));

        if (user == null) {
            throw new UnknownAccountException("account not exist");
        }
        if (user.getStatus() == -1) {
            throw new LockedAccountException("account is locked");
        }

        AccountProfile accountProfile = new AccountProfile();
        BeanUtil.copyProperties(user, accountProfile);

        return new SimpleAuthenticationInfo(accountProfile, jwtToken.getCredentials(), getName());

    }
}

package com.shenpotato.vueblog.util;

import com.shenpotato.vueblog.shiro.AccountProfile;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;

public class ShiroUtil {

    public static AccountProfile getProfile(){
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}

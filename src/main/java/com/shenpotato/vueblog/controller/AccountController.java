package com.shenpotato.vueblog.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenpotato.vueblog.common.dto.LoginDto;
import com.shenpotato.vueblog.common.dto.RegisterDto;
import com.shenpotato.vueblog.common.lang.Result;
import com.shenpotato.vueblog.entity.User;
import com.shenpotato.vueblog.service.UserService;
import com.shenpotato.vueblog.util.JwtUtils;
import freemarker.template.utility.StringUtil;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@RestController
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse httpServletResponse) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        Assert.notNull(user, "user not exist");

        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("password incorrect");
        }

        String jwt = jwtUtils.generateToken(user.getId());

        httpServletResponse.setHeader("Authorization", jwt);
        httpServletResponse.setHeader("Access-control-Expose-Headers", "Authorization");

        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map()
        );
    }

    @PostMapping("/register")
    public Result register(@Validated @RequestBody RegisterDto registerDto, HttpServletResponse httpServletResponse) {

        if(!registerDto.getPassword().equals(registerDto.getRepassword())){
            return Result.fail("two passord not the same");
        }

        User newUser = new User();
        newUser.setUsername(registerDto.getUsername());
        newUser.setEmail(registerDto.getEmail());
        newUser.setAvatar(registerDto.getAvatar());
        newUser.setPassword(SecureUtil.md5(registerDto.getPassword()));
        newUser.setCreated(LocalDateTime.now());
        newUser.setStatus(0);

        userService.save(newUser);

        return Result.success(MapUtil.builder()
                .put("id", newUser.getId())
                .put("username", newUser.getUsername())
                .put("avatar", newUser.getAvatar())
                .put("email", newUser.getEmail())
                .map()

        );
    }

    @RequiresAuthentication
    @PostMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.success(null);
    }
}

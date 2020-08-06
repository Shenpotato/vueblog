package com.shenpotato.vueblog.controller;

import com.shenpotato.vueblog.common.lang.Result;
import com.shenpotato.vueblog.entity.User;
import com.shenpotato.vueblog.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Shen_potato
 * @since 2020-06-19
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @RequiresAuthentication
    @GetMapping("/{id}")
    public Result index(@PathVariable("id") Long id){   //@PathVariable: send the url param to method
        User user = userService.getById(id);
        return Result.success(user);
    }

    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user){
        return Result.success(user);
    }

}

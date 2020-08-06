package com.shenpotato.vueblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenpotato.vueblog.entity.User;
import com.shenpotato.vueblog.mapper.UserMapper;
import com.shenpotato.vueblog.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Shen_potato
 * @since 2020-06-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

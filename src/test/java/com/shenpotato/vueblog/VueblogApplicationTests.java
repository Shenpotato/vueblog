package com.shenpotato.vueblog;

import com.shenpotato.vueblog.entity.User;
import com.shenpotato.vueblog.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class VueblogApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        userMapper.selectById(1);
    }

}

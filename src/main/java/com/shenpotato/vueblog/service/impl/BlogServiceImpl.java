package com.shenpotato.vueblog.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenpotato.vueblog.entity.Blog;
import com.shenpotato.vueblog.mapper.BlogMapper;
import com.shenpotato.vueblog.service.BlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}

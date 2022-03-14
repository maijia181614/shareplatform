package com.shareplatform.demo.service.impl;

import com.shareplatform.demo.entity.User;
import com.shareplatform.demo.mapper.UserMapper;
import com.shareplatform.demo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mjq
 * @since 2021-06-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findAllUserInfoByUsername(String username){
     return   userMapper.findAllUserInfoByUsername(username);
    }

    @Override
    public void register(User user) {
        userMapper.register(user);
    }

}

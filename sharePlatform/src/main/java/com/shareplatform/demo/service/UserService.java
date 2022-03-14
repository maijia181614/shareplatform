package com.shareplatform.demo.service;

import com.shareplatform.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mjq
 * @since 2021-06-07
 */
public interface UserService extends IService<User> {

    public User findAllUserInfoByUsername(String username);

    public void register(User user);
}

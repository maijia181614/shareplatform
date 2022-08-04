package com.shareplatform.demo.mapper;

import com.shareplatform.demo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mjq
 * @since 2021-06-07
 */
public interface UserMapper extends BaseMapper<User> {

    public User findAllUserInfoByUsername(String userName);

    public void register(User user);

    public void editUserInfo(User user);
}

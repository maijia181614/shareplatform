package com.shareplatform.demo.service;

import com.shareplatform.demo.entity.User;

public interface UserCacheService {

    /**
     * 删除用户缓存
     * @param userId
     */
    void delUser(Integer userId);

    /**
     * 获取用户信息
     * @param userName
     * @return
     */
    User getUser(String userName);

    /**
     * 设置用户信息缓存
     * @param user
     */
    void setUser(User user);

    /**
     * 设置验证码缓存
     * @param telephone
     * @param authCode
     */
    void setAuthCode(String telephone, String authCode);

    /**
     * 获取验证码
     * @param telephone
     * @return
     */
    String getAuthCode(String telephone);
}

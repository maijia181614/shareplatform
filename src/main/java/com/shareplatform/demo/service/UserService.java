package com.shareplatform.demo.service;

import com.shareplatform.demo.common.api.CommonResult;
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

    public void editUserInfo(User user, Integer userId);

    /**
     * 生成验证码
     * @param telephone
     * @return
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码与手机号是否匹配
     * @param telephone
     * @param authCode
     * @return
     */
    CommonResult verifyAuthCode(String telephone,String authCode);
}

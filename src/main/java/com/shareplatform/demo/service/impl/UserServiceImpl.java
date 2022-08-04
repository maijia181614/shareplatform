package com.shareplatform.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shareplatform.demo.common.api.CommonResult;
import com.shareplatform.demo.common.exception.Asserts;
import com.shareplatform.demo.entity.User;
import com.shareplatform.demo.mapper.UserMapper;
import com.shareplatform.demo.service.RedisService;
import com.shareplatform.demo.service.UserCacheService;
import com.shareplatform.demo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

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

    @Autowired
    private UserCacheService userCacheService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Autowired
    private RedisService redisService;

    @Override
    public User findAllUserInfoByUsername(String username){
        User user = userCacheService.getUser(username);
        if(user != null){
            return user;
        }else {
            user = userMapper.findAllUserInfoByUsername(username);
            if(user != null){
                userCacheService.setUser(user);
                return user;
            }else {
                return null;
            }
        }
    }

    @Override
    public void register(User user) {
        userMapper.register(user);
    }

    @Override
    public void editUserInfo(User user, Integer userId) {
        user.setUserId(userId);
        User tmp = userMapper.selectById(userId);
        if(tmp != null){
            userMapper.editUserInfo(user);
            userCacheService.delUser(userId);
        }else {
            Asserts.fail("该账号不存在");
            return;
        }
    }

    @Override
    public CommonResult generateAuthCode(String telephone) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for(int i = 0;i<6;i++){
            stringBuilder.append(random.nextInt(10));
        }
        //验证码绑定手机号并存储到redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE+telephone,stringBuilder.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE+telephone,AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(stringBuilder.toString(),"验证码获取成功");
    }

    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {
        if(StringUtils.isEmpty(authCode)){
            return CommonResult.failed("请输入验证码");
        }
        String realAuthCode = (String) redisService.get(REDIS_KEY_PREFIX_AUTH_CODE+telephone);
        boolean result = authCode.equals(realAuthCode);
        if(result){
            return CommonResult.success(null,"验证码验证成功");
        }else {
            return CommonResult.failed("验证码不正确");
        }
    }
}

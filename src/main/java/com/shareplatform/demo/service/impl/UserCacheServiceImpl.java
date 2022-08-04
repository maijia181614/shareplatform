package com.shareplatform.demo.service.impl;

import com.shareplatform.demo.entity.User;
import com.shareplatform.demo.mapper.UserMapper;
import com.shareplatform.demo.service.RedisService;
import com.shareplatform.demo.service.UserCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCacheServiceImpl implements UserCacheService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserMapper userMapper;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long  REDIS_EXPIRE;
    @Value("${redis.expire.authCode}")
    private Long REDIS_EXPIRE_AUTH_CODE;
    @Value("${redis.key.user}")
    private String REDIS_KEY_USER;
    @Value("${redis.key.authCode}")
    private String REDIS_KEY_AUTH_CODE;

    @Override
    public void delUser(Integer userId) {
        User user = userMapper.selectById(userId);
        if(user != null){
            String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + user.getUserName();
            if(redisService.del(key)){
//                System.out.println("redis删除成功");
            }
        }
    }

    @Override
    public User getUser(String userName) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + userName;
        return (User) redisService.get(key);
    }

    @Override
    public void setUser(User user) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + user.getUserName();
        redisService.set(key, user, REDIS_EXPIRE);
    }

    @Override
    public void setAuthCode(String telephone, String authCode) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_AUTH_CODE + ":" + telephone;
        redisService.set(key, authCode, REDIS_EXPIRE_AUTH_CODE);
    }

    @Override
    public String getAuthCode(String telephone) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_AUTH_CODE + ":" +telephone;
        return (String) redisService.get(key);
    }
}

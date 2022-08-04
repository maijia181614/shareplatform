package com.shareplatform.demo.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shareplatform.demo.common.api.CommonResult;
import com.shareplatform.demo.entity.User;
import com.shareplatform.demo.service.RedisService;
import com.shareplatform.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import springfox.documentation.service.Tags;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mjq
 * @since 2021-06-07
 */
@RestController
@RequestMapping("/user")
@Api(tags = "UserController",description = "会员管理")
public class UserController {

    @Autowired
    private UserService userService;

    /***
    * @description: 获取用户信息
    * @param
    * @return java.lang.String
    * @throws 
    * @author mjq
    * @date 2021/06/18 09:53
    */
    @RequestMapping("/userInfo")
    public String userInfo(){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        String result;
        if(null!=user){
            User tmp=new User();
            tmp.setName(user.getName());
            tmp.setPhone(user.getPhone());
            tmp.setAddress(user.getAddress());
            tmp.setSex(user.getSex());
            tmp.setUserId(user.getUserId());
            result=JSON.toJSONString(tmp);
            return result;
        }
        return "not login";
    }

    /***
    * @description: 判断是否为管理员
    * @param []
    * @return boolean
    * @throws 
    * @author mjq
    * @date 2021/06/18 09:53
    */
    @RequestMapping("/isAdmin")
    public boolean isAdmin(){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        boolean isAdmin=SecurityUtils.getSubject().hasRole("admin");
        return isAdmin;
    }


    @RequestMapping("/editUserInfo")
    public String editUserInfo(@RequestBody User user){
        User tmp = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        String result;
        System.out.println(user);
        if(null!=user){
            Integer id=tmp.getUserId();
            userService.editUserInfo(user,id);
            return "success";
        }else {
            return "fail";
        }
    }

    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode",method = RequestMethod.GET)
    public CommonResult getAuthCode(@RequestParam String telephone){
        return userService.generateAuthCode(telephone);
    }

    @ApiOperation("更改密码，需要判断验证码")
    @RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
    public CommonResult updatePassword(@RequestParam String telephone,@RequestParam String authCode){
        return userService.verifyAuthCode(telephone,authCode);
    }


}


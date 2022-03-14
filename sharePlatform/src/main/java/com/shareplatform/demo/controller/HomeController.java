package com.shareplatform.demo.controller;

import com.shareplatform.demo.entity.User;
import com.shareplatform.demo.entity.UserRole;
import com.shareplatform.demo.service.RolePermissionService;
import com.shareplatform.demo.service.UserRoleService;
import com.shareplatform.demo.service.UserService;
import com.shareplatform.demo.util.PasswordHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    public PasswordHelper passwordHelper;

    /***
    * @description: 登录
    * @param [username, password]
    * @return java.lang.String
    * @throws 
    * @author mjq
    * @date 2021/06/13 17:32
    */
    @RequestMapping("/login")
    public String login(@RequestBody Map<String,Object> map){
        String userName= (String) map.get("userName");
        String passWord= (String) map.get("passWord");
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(userName,passWord);
        Subject subject= SecurityUtils.getSubject();
        try{
            System.out.println("执行login前:");
            subject.login(usernamePasswordToken);
        }catch (IncorrectCredentialsException ice){
            return "password error!";
        }catch (UnknownAccountException uae){
            return "username error!";
        }

        User user=userService.findAllUserInfoByUsername(userName);
        subject.getSession().setAttribute("user",user);
        Serializable tokenId = subject.getSession().getId();
        boolean isAdmin=SecurityUtils.getSubject().hasRole("admin");
        System.out.println(isAdmin);
        System.out.println(SecurityUtils.getSubject().getSession().getAttribute("roleList"));
        return String.valueOf(tokenId);
    }

    /***
    * @description: 注册
    * @param [username, password]
    * @return java.lang.String
    * @throws 
    * @author mjq
    * @date 2021/06/13 18:45
    */
    @RequestMapping("/register")
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public String register(@RequestBody  User user){

        System.out.println(user);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setCreateTime(sdf.format(d));
        passwordHelper.encryptPassword(user);

        userService.register(user);
        UserRole userRole=new UserRole();
        userRole.setUserId(user.getUserId());
        userRole.setRoleId(1);
        userRoleService.save(userRole);
        if(null!=user.getUserId()){
            return "success";
        }
        return "fail";
    }


    /***
    * @description: 登出
    * @param []
    * @return java.lang.String
    * @throws 
    * @author mjq
    * @date 2021/06/15 17:10
    */
    @RequestMapping("/logout")
    public String register(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "logout success";
    }
}

package com.shareplatform.demo.config;

import com.shareplatform.demo.entity.Permission;
import com.shareplatform.demo.entity.Role;
import com.shareplatform.demo.entity.User;
import com.shareplatform.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class EnceladusShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /***
    * @description: 进行权限校验时调用
    * @param [principalCollection]
    * @return org.apache.shiro.authz.AuthorizationInfo
    * @throws 
    * @author mjq
    * @date 2021/06/12 23:10
    */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        String userName=(String) principalCollection.getPrimaryPrincipal();
        User user=userService.findAllUserInfoByUsername(userName);
        System.out.println("权限校验时调用findAlluserInfo");
        List<String> stringRoleList=new ArrayList<>();
        List<String> stringPermissionList=new ArrayList<>();
        List<Role> roleList=user.getRoleList();

        for(Role role:roleList){
            stringRoleList.add(role.getName());

            List<Permission> permissionList=role.getPermissionList();
            for(Permission p:permissionList){
                if (null != p) {
                    stringPermissionList.add(p.getName());
                }
            }
        }
        simpleAuthorizationInfo.addRoles(stringRoleList);
        simpleAuthorizationInfo.addStringPermissions(stringPermissionList);
        SecurityUtils.getSubject().getSession().setAttribute("roleList", stringRoleList);
        return simpleAuthorizationInfo;
    }

    /***
    * @description: 登录校验调用
    * @param [token]
    * @return org.apache.shiro.authc.SimpleAuthenticationInfo
    * @throws 
    * @author mjq
    * @date 2021/06/12 23:47
    */
    @Override
    protected SimpleAuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username=(String) token.getPrincipal();
        System.out.println("principal:"+username);
        User user=userService.findAllUserInfoByUsername(username);
        System.out.println("身份校验时调用findAlluserInfo");
//        System.out.println("principal password:"+user.getPassWord());
        if(user != null){
            String passWord=user.getPassWord();
            if(null==passWord|| "".equals(passWord)){
                return null;
            }
            if(null==username|| "".equals(username)){
                return null;
            }
        }else{
            return null;
        }



        System.out.println("principal userId:"+user.getUserId());
        System.out.println("principal createTime:"+user.getCreateTime());

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassWord(),
                ByteSource.Util.bytes(user.getSalt()), getName());
        return authenticationInfo;
    }
}

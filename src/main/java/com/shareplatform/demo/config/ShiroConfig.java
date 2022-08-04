package com.shareplatform.demo.config;

import com.shareplatform.demo.util.PasswordHelper;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, Filter> customFilterMap = new LinkedHashMap<>();
        customFilterMap.put("corsAuthenticationFilter",new CORSAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(customFilterMap);

        Map<String,String> filterChainDefinitionMap=new LinkedHashMap<>();

        //authc : url定义必须通过认证才可以访问
        //anon  : url可以匿名访问
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthor");
        shiroFilterFactoryBean.setSuccessUrl("/home/index");


        //过滤器  按照顺序进行拦截
//        authc：所有已登陆用户可访问
//
//roles：有指定角色的用户可访问，通过[ ]指定具体角色，这里的角色名称与数据库中配置一致
//
//perms：有指定权限的用户可访问，通过[ ]指定具体权限，这里的权限名称与数据库中配置一致
//
//anon：所有用户可访问，通常作为指定页面的静态资源时使用
//                filterChainDefinitionMap.put("/authc/admin", "roles[admin]");
//        filterChainDefinitionMap.put("/authc/renewable", "perms[Create,Update]");
//        filterChainDefinitionMap.put("/authc/removable", "perms[Delete]");
        filterChainDefinitionMap.put("/*", "anon");
        filterChainDefinitionMap.put("/logout","logout");
        filterChainDefinitionMap.put("/login","anon");
        filterChainDefinitionMap.put("/cart","roles[member]");
        filterChainDefinitionMap.put("/myOrder","roles[member]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(PasswordHelper.ALGORITHM_NAME);
        hashedCredentialsMatcher.setHashIterations(PasswordHelper.HASH_ITERATIONS);
        return hashedCredentialsMatcher;
    }

    @Bean
    public EnceladusShiroRealm shiroRealm(){
        EnceladusShiroRealm enceladusShiroRealm=new EnceladusShiroRealm();
        enceladusShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return enceladusShiroRealm;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    public PasswordHelper passwordHelper(){
        return new PasswordHelper();
    }

    /***
    * @description: 将重写的shiro session注册
    * @param
    * @return org.apache.shiro.session.mgt.SessionManager
    * @throws 
    * @author mjq
    * @date 2021/06/13 20:36
    */
    @Bean
    public SessionManager sessionManager(){
        ShiroSession shiroSession=new ShiroSession();
        shiroSession.setSessionDAO(new EnterpriseCacheSessionDAO());
        return shiroSession;
    }
}

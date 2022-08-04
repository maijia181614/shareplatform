package com.shareplatform.demo.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shareplatform.demo.entity.Entrust;
import com.shareplatform.demo.entity.OrderItem;
import com.shareplatform.demo.entity.User;
import com.shareplatform.demo.service.EntrustService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mjq
 * @since 2021-06-17
 */
@RestController
@RequestMapping("/entrust")
public class EntrustController {

    @Autowired
    private EntrustService entrustService;

    /***
    * @description: 用户发布一个委托
    * @param [entrustMap]
    * @return java.lang.String
    * @throws 
    * @author mjq
    * @date 2021/06/18 09:50
    */
    @RequestMapping("/add")
    public String add(@RequestBody Map<String,Object> entrustMap){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        if(null!=user){
            Entrust entrust=new Entrust();
            entrust.setEGoodsName((String) entrustMap.get("eGoodsName"));
            entrust.setEGoodsIntro((String) entrustMap.get("eGoodsIntro"));
            entrust.setEGoodsCategory((Integer) entrustMap.get("eGoodsCategory"));
            entrust.setEGoodsCoverImg((String) entrustMap.get("eGoodsCoverImg"));
            entrust.setEGoodsCarousel((String) entrustMap.get("eGoodsCarousel"));
            entrust.setEGoodsDetailContent((String) entrustMap.get("eGoodsDetailContent"));
            entrust.setEGoodsPrice(Double.parseDouble((String) entrustMap.get("eGoodsPrice")));
            entrust.setEStockNum((Integer) entrustMap.get("eStockNum"));
            System.out.println(entrust);
            entrustService.save(entrust);
            return entrust.getEntrustId().toString();
        }else {
            return "fail";
        }
    }

    /***
    * @description: 列出所有委托
    * @param []
    * @return java.lang.String
    * @throws 
    * @author mjq
    * @date 2021/06/18 09:50
    */
    @RequestMapping("/list")
    public String list(){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        boolean isAdmin=SecurityUtils.getSubject().hasRole("admin");
        if(null!=user){
            if(isAdmin){
                List<Entrust> entrustList=entrustService.list();
                return JSON.toJSONString(entrustList);
            }else {
                return "fail";
            }
        }else {
            return "fail";
        }
    }

    /***
    * @description: 根据用户id列出委托
    * @param [map]
    * @return java.lang.String
    * @throws 
    * @author mjq
    * @date 2021/06/18 09:51
    */
    @RequestMapping("/listById")
    public String listById(@RequestBody Map<String,Object> map){
        Integer id= (Integer) map.get("entrustId");
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        System.out.println("编辑委托："+id);
        boolean isAdmin=SecurityUtils.getSubject().hasRole("admin");
        if(null!=user){
            if(isAdmin){
                QueryWrapper<Entrust> entrustQueryWrapper = new QueryWrapper<>();
                entrustQueryWrapper.eq("entrust_id",id);
                Entrust entrust=entrustService.getOne(entrustQueryWrapper);
                return JSON.toJSONString(entrust);
            }else {
                return "fail";
            }
        }else {
            return "fail";
        }
    }

}


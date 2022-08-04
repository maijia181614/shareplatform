package com.shareplatform.demo.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shareplatform.demo.entity.CartItem;
import com.shareplatform.demo.entity.OrderItem;
import com.shareplatform.demo.entity.User;
import com.shareplatform.demo.service.OrderItemService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
@RequestMapping("/orderItem")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    /***
    * @description: 增加物品项
    * @param [orderItem]
    * @return java.lang.String
    * @throws 
    * @author mjq
    * @date 2021/06/16 17:49
    */
    @RequestMapping("/add")
    public String add(@RequestBody OrderItem orderItem){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        if(null!=user){
            System.out.println(orderItem);
            orderItem.setUserId(user.getUserId());
            orderItemService.save(orderItem);
            return orderItem.getOrderItemId().toString();
        }else {
            return "fail";
        }
    }

    /***
    * @description: 根据订单列出订单项
    * @param [map]
    * @return java.lang.String
    * @throws 
    * @author mjq
    * @date 2021/06/18 09:52
    */
    @RequestMapping("/listByOrder")
    public String listByOrder(@RequestBody Map<String, Object> map){
        Integer orderId= (Integer) map.get("orderId");
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        if(null!=user){
            System.out.println(orderId);
            QueryWrapper<OrderItem> wrapper = new QueryWrapper<>();
            wrapper.eq("order_id",orderId);
            List<OrderItem> orderItemList=orderItemService.list(wrapper);
            return JSON.toJSONString(orderItemList);
        }else {
            return "fail";
        }
    }

    /***
    * @description: 根据用户列出订单项
    * @param []
    * @return java.util.List<com.shareplatform.demo.entity.OrderItem>
    * @throws 
    * @author mjq
    * @date 2021/06/18 09:53
    */
    @RequestMapping("/listByUser")
    public List<OrderItem> listByUser(){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        if(null!=user){
            QueryWrapper<OrderItem> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id",user.getUserId());
            List<OrderItem> orderItemList=orderItemService.list(wrapper);
            return orderItemList;
        }else {
            return null;
        }
    }
}


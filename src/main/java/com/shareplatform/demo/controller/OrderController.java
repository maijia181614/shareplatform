package com.shareplatform.demo.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shareplatform.demo.entity.*;
import com.shareplatform.demo.service.CartItemService;
import com.shareplatform.demo.service.OrderItemService;
import com.shareplatform.demo.service.OrderService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mjq
 * @since 2021-06-07
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private CartItemService cartItemService;

    /***
    * @description: 添加订单
    * @param [order]
    * @return java.lang.String
    * @throws 
    * @author mjq
    * @date 2021/06/18 09:52
    */
    @RequestMapping("/add")
    public String add(@RequestBody Order order){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        if(null!=user){
            System.out.println(order);
            order.setUserId(user.getUserId());
            order.setUserAddress(user.getAddress());
            order.setUserName(user.getUserName());
            order.setUserPhone(user.getPhone());
            order.setOrderNum(createNum());
            orderService.save(order);
            return order.getOrderId().toString();
        }else {
            return "fail";
        }
    }

    /***
    * @description: 根据用户列出订单列表
    * @param []
    * @return java.lang.String
    * @throws 
    * @author mjq
    * @date 2021/06/18 09:52
    */
    @RequestMapping("/listByUser")
    public String listByUser(){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        if(null!=user){
            System.out.println(user.getUserId());
            QueryWrapper<Order> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id",user.getUserId());
            List<Order> orderList=orderService.list(wrapper);
            for(Order order : orderList){
                if(null!=order.getOrderId()){
                    QueryWrapper<OrderItem> orderItemWrapper = new QueryWrapper<>();
                    orderItemWrapper.eq("order_id",order.getOrderId());
                    List<OrderItem> orderItemList=orderItemService.list(orderItemWrapper);
                    order.setOrderItemList(orderItemList);
                }
            }
            return JSON.toJSONString(orderList);
        }else {
            return "fail";
        }
    }

    /***
    * @description: 随机生成订单号
    * @param []
    * @return java.lang.String
    * @throws
    * @author mjq
    * @date 2021/06/17 11:14
    */
    private String createNum(){
        // 获取当前时间
        SimpleDateFormat dmDate = new SimpleDateFormat("yyyyMMddHHmmss");

        // 定义随机数
        Random random = new Random();
        // 随机数长度位数
        int ran = random.nextInt(1000000);

        Date date = new Date();
        String dateran = dmDate.format(date);
        String randomMath = dateran+ran;
        return randomMath;
    }

    /***
    * @description: 从购物车直接下单
    * @param [map]
    * @return java.lang.String
    * @throws 
    * @author mjq
    * @date 2021/06/18 09:52
    */
    @RequestMapping("/addOrderByCart")
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public String addOrderByCart(@RequestBody Map<String,Object> map) throws ParseException {
        List<CartItem> cartItemList=JSON.parseArray(JSON.toJSONString(map.get("cartItemList")), CartItem.class);
        Double totalPrice= Double.parseDouble((String) map.get("totalPrice"));
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        if(null!=user){
            System.out.println(cartItemList);
            System.out.println(totalPrice);
            Order order=new Order();
            order.setOrderNum(createNum());
            order.setUserId(user.getUserId());
            order.setTotalPrice(totalPrice);
            order.setPayStatus(1);
            Date dNow = new Date( );
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
            order.setPayTime(ft.format(dNow));
            order.setOrderStatus(1);
            order.setUserName(user.getUserName());
            order.setUserPhone(user.getPhone());
            order.setUserAddress(user.getAddress());
            order.setCreateTime(ft.format(dNow));
            boolean isSaveOrder=orderService.save(order);
            if(isSaveOrder){
                Integer orderId=order.getOrderId();
                for(CartItem item:cartItemList){
                    OrderItem tmp=new OrderItem();
                    tmp.setOrderId(orderId);
                    tmp.setGoodsId(item.getGoodsId());
                    tmp.setGoodsName(item.getGoodsName());
                    tmp.setGoodsCoverImg(item.getGoodsCoverImg());
                    tmp.setPrice(item.getSingleTotalPrice());
                    tmp.setGoodsCount(item.getGoodsCount());
                    String format = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
                    SimpleDateFormat sdf = new SimpleDateFormat(format);
                    tmp.setCreateTime(item.getCreateTime().split("T")[0]);
                    tmp.setEndTime(item.getEndTime().split("T")[0]);
                    tmp.setUserId(user.getUserId());
                    boolean isSaveOrderItem=orderItemService.save(tmp);
                    if(isSaveOrderItem){
                        QueryWrapper<CartItem> cartItemQueryWrapper=new QueryWrapper<>();
                        cartItemQueryWrapper.eq("cart_item_id",item.getCartItemId());
                        cartItemService.remove(cartItemQueryWrapper);
                    }else {
                        return "fail";
                    }
                }

            }
            return "success";
        }else {
            return "fail";
        }
    }
}


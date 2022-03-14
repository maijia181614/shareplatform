package com.shareplatform.demo.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shareplatform.demo.entity.CartItem;
import com.shareplatform.demo.entity.Goods;
import com.shareplatform.demo.entity.Order;
import com.shareplatform.demo.entity.User;
import com.shareplatform.demo.service.CartItemService;
import com.shareplatform.demo.service.GoodsService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("/cart")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private GoodsService goodsService;

    /***
    * @description: 添加租赁物品到购物车
    * @param [map]
    * @return java.lang.String
    * @throws 
    * @author mjq
    * @date 2021/06/18 09:49
    */
    @RequestMapping("/add")
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public String add(@RequestBody Map<String,Object> map){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        if(null!=user){
            Integer goodsId= (Integer) map.get("goodsId");
            QueryWrapper<Goods> goodsQueryWrapper=new QueryWrapper<>();
            goodsQueryWrapper.eq("goods_id",goodsId);
            Goods goods=goodsService.getOne(goodsQueryWrapper);
            System.out.println(goods);
            CartItem cartItem=new CartItem();
            cartItem.setUserId(user.getUserId());
            cartItem.setGoodsId(goods.getGoodsId());
            cartItem.setGoodsCount(1);
            cartItem.setIsDeleted(0);
            cartItem.setPrice(goods.getGoodsPrice());
            cartItem.setGoodsCoverImg(goods.getGoodsCoverImg());
            cartItem.setGoodsName(goods.getGoodsName());
            System.out.println(cartItem);
            cartItemService.save(cartItem);
            return cartItem.getCartItemId().toString();
        }else {
            return "fail";
        }
    }

    /***
    * @description: 读取购物车
    * @param []
    * @return java.lang.String
    * @throws 
    * @author mjq
    * @date 2021/06/18 09:49
    */
    @RequestMapping("/list")
    public String list(){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        if(null!=user){
            Integer id=user.getUserId();
            System.out.println("id:"+id);
            QueryWrapper<CartItem> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id",id);
            List<CartItem> cartItemList=cartItemService.list(wrapper);
            System.out.println(cartItemList.toString());
            return JSON.toJSONString(cartItemList);
        }else {
            return "fail";
        }
    }

    /***
    * @description: 删除购物车租赁物品
    * @param [map]
    * @return java.lang.String
    * @throws 
    * @author mjq
    * @date 2021/06/18 09:49
    */
    @RequestMapping("/delete")
    public String delete(@RequestBody Map<String, Object> map){
        Integer itemId= (Integer) map.get("cartItemId");
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        if(null!=user){
            Integer id=user.getUserId();
            System.out.println("id:"+id);
            QueryWrapper<CartItem> wrapper = new QueryWrapper<>();
            wrapper.and(i->i.eq("user_id",id).eq("cart_item_id",itemId));
            boolean isDelete=cartItemService.remove(wrapper);
            if(isDelete){
                return "success";
            }else {
                return "fail";
            }
        }else {
            return "fail";
        }
    }

    /***
    * @description: 修改购物车项目
    * @param [cartItem]
    * @return java.lang.String
    * @throws 
    * @author mjq
    * @date 2021/06/18 09:50
    */
    @RequestMapping("/update")
    public String update(@RequestBody CartItem cartItem){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        if(null!=user){
            Integer id=user.getUserId();
            Integer carItemId=cartItem.getCartItemId();
            System.out.println("id:"+id);
            QueryWrapper<CartItem> wrapper = new QueryWrapper<>();
            wrapper.and(i->i.eq("user_id",id).eq("cart_item_id", carItemId));
            boolean isUpdate=cartItemService.update(cartItem,wrapper);
            if(isUpdate){
                return "success";
            }else {
                return "fail";
            }
        }else {
            return "fail";
        }
    }

}


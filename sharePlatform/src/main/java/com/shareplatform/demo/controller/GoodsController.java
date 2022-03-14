package com.shareplatform.demo.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shareplatform.demo.entity.Category;
import com.shareplatform.demo.entity.Entrust;
import com.shareplatform.demo.entity.Goods;
import com.shareplatform.demo.entity.User;
import com.shareplatform.demo.service.EntrustService;
import com.shareplatform.demo.service.GoodsService;
import io.swagger.models.auth.In;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private EntrustService entrustService;

    /***
    * @description: 根据关键字搜索租赁物品
    * @param [map]
    * @return java.lang.String
    * @throws 
    * @author mjq
    * @date 2021/06/18 09:51
    */
    @RequestMapping("/search")
    @Cacheable(value = "platRedis",key = "#map")
    public String search(@RequestBody Map<String,Object> map){
        String key= (String) map.get("key");
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.like("goods_name",key);
        System.out.println(key);
        List<Goods> goodsList=goodsService.list(wrapper);
        return JSON.toJSONString(goodsList);
    }

    /***
    * @description: 获取物品详情
    * @param [map]
    * @return java.lang.String
    * @throws 
    * @author mjq
    * @date 2021/06/18 09:51
    */
    @RequestMapping("/detail")
    public String detail(@RequestBody Map<String,Object> map){
        Integer key= (Integer) map.get("key");
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq("goods_id",key);
        System.out.println(key);
        Goods goods=goodsService.getOne(wrapper);
        return JSON.toJSONString(goods);
    }

    /***
    * @description: 从委托添加租赁物品
    * @param [map]
    * @return java.lang.String
    * @throws 
    * @author mjq
    * @date 2021/06/18 09:51
    */
    @RequestMapping("/add")
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public String add(@RequestBody Map<String,Object> map){
        Goods goods=JSON.parseObject(JSON.toJSONString(map.get("entrust")),Goods.class);
        Double price=Double.valueOf((Integer)map.get("itPrice"));
        Integer entrustId=(Integer)map.get("entrustId");
        goods.setGoodsPrice(price);
        System.out.println(price);
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        boolean isAdmin=SecurityUtils.getSubject().hasRole("admin");
        if(null!=user){
            if(isAdmin){
                System.out.println(goods);
                goods.setGoodsStatus(1);
                goodsService.save(goods);
                entrustService.removeById(entrustId);
                return goods.getGoodsId().toString();
            }else {
                return "not admin";
            }
        }else {
            return "fail";
        }

    }

    @RequestMapping("/searchByOrder")
    public String searchByOrder(@RequestBody Map<String,Object> map){
        String key= (String) map.get("key");
        String order= (String) map.get("order");
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.like("goods_name",key);
        wrapper.orderByAsc(order);
        System.out.println(key);
        List<Goods> goodsList=goodsService.list(wrapper);
        return JSON.toJSONString(goodsList);
    }
}


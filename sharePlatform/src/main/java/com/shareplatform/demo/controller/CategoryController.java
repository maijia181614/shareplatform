package com.shareplatform.demo.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shareplatform.demo.entity.Category;
import com.shareplatform.demo.service.CartItemService;
import com.shareplatform.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /***
    * @description: 列出所有分类
    * @param []
    * @return java.lang.String
    * @throws 
    * @author mjq
    * @date 2021/06/18 09:50
    */
    @RequestMapping("/list")
    public String list(){
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        QueryWrapper<Category> childrenWrapper = new QueryWrapper<>();
        Map<String,Object> result=new HashMap<>();
        wrapper.eq("parent_id", 0);
        List<Category> categoryParentList=categoryService.list(wrapper);
        childrenWrapper.ne("parent_id", 0);
        List<Category> categoryChildrenList=categoryService.list(childrenWrapper);
        result.put("categoryParentList",categoryParentList);
        result.put("categoryChildrenList",categoryChildrenList);
        return JSON.toJSONString(result);
    }

    /***
    * @description: 列出所有子分类
    * @param []
    * @return java.lang.String
    * @throws 
    * @author mjq
    * @date 2021/06/18 09:50
    */
    @RequestMapping("/listChildrenCategory")
    public String listChildrenCategory(){
        QueryWrapper<Category> childrenWrapper = new QueryWrapper<>();
        childrenWrapper.eq("category_level", 2);
        List<Category> categoryChildrenList=categoryService.list(childrenWrapper);
        return JSON.toJSONString(categoryChildrenList);
    }
}


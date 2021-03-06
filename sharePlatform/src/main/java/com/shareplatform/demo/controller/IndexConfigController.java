package com.shareplatform.demo.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shareplatform.demo.entity.IndexConfig;
import com.shareplatform.demo.service.IndexConfigService;
import io.swagger.models.auth.In;
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
@RequestMapping("/index-config")
public class IndexConfigController {

    @Autowired
    private IndexConfigService indexConfigService;

    /***
    * @description: 获取推荐列表
    * @param [map]
    * @return java.lang.String
    * @throws 
    * @author mjq
    * @date 2021/06/19 16:24
    */
    @RequestMapping("/list")
    public String list(@RequestBody Map<String,Object> map){
        Integer indexType= (Integer) map.get("indexType");
        QueryWrapper<IndexConfig> indexConfigQueryWrapper=new QueryWrapper<>();
        indexConfigQueryWrapper.eq("config_type",indexType);
        List<IndexConfig> indexConfigList= indexConfigService.list(indexConfigQueryWrapper);
        if(null!=indexConfigList){
            return JSON.toJSONString(indexConfigList);
        }else {
            return "fail";
        }
    }
}


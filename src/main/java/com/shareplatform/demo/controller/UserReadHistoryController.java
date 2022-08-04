package com.shareplatform.demo.controller;

import com.shareplatform.demo.common.api.CommonPage;
import com.shareplatform.demo.common.api.CommonResult;
import com.shareplatform.demo.domain.EsProduct;
import com.shareplatform.demo.entity.Goods;
import com.shareplatform.demo.entity.User;
import com.shareplatform.demo.entity.UserReadHistory;
import com.shareplatform.demo.service.EsProductService;
import com.shareplatform.demo.service.GoodsService;
import com.shareplatform.demo.service.UserReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "UserReadHistoryController", description = "用户商品浏览记录管理")
@RequestMapping("/userReadHistory")
public class UserReadHistoryController {
    @Autowired
    private UserReadHistoryService userReadHistoryService;


    @ApiOperation("创建浏览记录")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody UserReadHistory userReadHistory) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        userReadHistory.setUserId(user.getUserId());
        userReadHistory.setUserName(user.getUserName());
        int count = userReadHistoryService.create(userReadHistory);
        if(count>0){
            return CommonResult.success(count);
        }else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除浏览记录")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestParam("ids")List<String> ids){
        int count = userReadHistoryService.delete(ids);
        if(count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation("分页获取浏览记录")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<UserReadHistory>> list(@RequestBody Map<String, Object> map){
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        Integer userId = user.getUserId();
        Page<UserReadHistory> userReadHistories = userReadHistoryService.list(userId, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(userReadHistories));
    }
}
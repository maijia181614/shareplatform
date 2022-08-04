package com.shareplatform.demo.controller;

import com.shareplatform.demo.common.api.CommonPage;
import com.shareplatform.demo.common.api.CommonResult;
import com.shareplatform.demo.domain.EsProduct;
import com.shareplatform.demo.service.EsProductService;
import com.shareplatform.demo.util.LogAspect;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 搜索商品管理controller
 *
 */
@RestController
@Api(tags = "EsProductController",description = "搜索商品管理")
@RequestMapping("/esProduct")
public class EsProductController {
    @Autowired
    private EsProductService esProductService;

    @ApiOperation(value = "导入所有商品数据到ES")
    @RequestMapping(value = "/importAll",method = RequestMethod.POST)
    public CommonResult<Integer> importAllList(){
        int count = esProductService.importAll();
        return CommonResult.success(count);
    }

    @ApiOperation(value = "根据id删除商品")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    public CommonResult<Object> delete(@PathVariable Long id){
        esProductService.delete(id);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据id批量删除商品")
    @RequestMapping(value = "/delete/batch",method = RequestMethod.POST)
    public CommonResult<Object> delete(@RequestParam("ids") List<Long> ids){
        esProductService.delete(ids);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据id创建商品")
    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
    public CommonResult<EsProduct> create(@PathVariable Long id){
        EsProduct esProduct = esProductService.create(id);
        if(esProduct != null){
            return CommonResult.success(esProduct);
        }else {
            return CommonResult.failed();
        }
    }


    @ApiOperation(value = "简单搜索")
    @RequestMapping(value = "/search/simple",method = RequestMethod.GET)
    public CommonResult<CommonPage<EsProduct>> search(@RequestParam(required = false) String keyword,
                                                      @RequestParam(required = false, defaultValue="0") Integer pageNum,
                                                      @RequestParam(required = false, defaultValue = "5") Integer pageSize){
        Page<EsProduct> esProductPage = esProductService.search(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(esProductPage));
    }

    @ApiOperation(value = "综合搜索")
    @ApiImplicitParam(name = "sort", value = "排序字段:0->按相关度; 1->按新品; 2->按价格从高到低; 3->按价格从低到高; ",
                        defaultValue = "0", allowableValues = "0,1,2,3", paramType = "query", dataType = "Integer")
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public CommonResult<CommonPage<EsProduct>> search(@RequestParam(required = false) String keyword,
                                                      @RequestParam(required = false) Integer productCategoryId,
                                                      @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                      @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                                                      @RequestParam(required = false, defaultValue = "0") Integer sort){
        Page<EsProduct> esProductPage = esProductService.search(keyword, productCategoryId, pageNum, pageSize, sort);
        return CommonResult.success(CommonPage.restPage(esProductPage));
    }

    @ApiOperation(value = "简单搜索,返回列表，不分页")
    @RequestMapping(value = "/search/simpleList",method = RequestMethod.POST)
    public CommonResult<List<EsProduct>> search(@RequestBody(required = false) String keyword){
        List<EsProduct> esProductList = esProductService.search(keyword);
        return CommonResult.success(esProductList);
    }



}

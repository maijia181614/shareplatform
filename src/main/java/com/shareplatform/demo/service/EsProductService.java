package com.shareplatform.demo.service;

import com.shareplatform.demo.domain.EsProduct;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 商品搜索管理Service
 */
public interface EsProductService {

    /**
     * 从数据库中导入信息到ES
     * @return
     */
    int importAll();

    /**
     * 根据id删除商品
     * @param id
     */
    void delete(Long id);

    /**
     * 根据id创建商品
     * @param id
     * @return
     */
    EsProduct create(Long id);

    /**
     * 批量删除商品
     * @param ids
     */
    void delete(List<Long> ids);

    /**
     * 根据关键词搜索商品名称或者简介
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<EsProduct> search(String keyword,Integer pageNum, Integer pageSize);

    /**
     * 综合搜索
     * @param keyword
     * @param productCategoryId
     * @param pageNum
     * @param pageSize
     * @param sort
     * @return
     */
    Page<EsProduct> search(String keyword, Integer productCategoryId, Integer pageNum, Integer pageSize, Integer sort);

    /**
     * 根据关键字搜索返回列表
     * @param keyword
     * @return
     */
    List<EsProduct> search(String keyword);

    }

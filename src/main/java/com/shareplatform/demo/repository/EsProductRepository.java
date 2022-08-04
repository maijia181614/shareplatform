package com.shareplatform.demo.repository;

import com.shareplatform.demo.domain.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 商品ES操作类
 */
public interface EsProductRepository extends ElasticsearchRepository<EsProduct,Long> {
    /**
     * 搜索查询
     * @param name 商品名称
     * @param intro 商品简介
     * @param page 分页信息
     * @return
     */
    Page<EsProduct> findByProductNameOrProductIntro(String name, String intro, Pageable page);

    /**
     * 搜索查询，返回列表，不分页
     * @param name
     * @param intro
     * @return
     */
    List<EsProduct> findByProductNameOrProductIntro(String name, String intro);

}

package com.shareplatform.demo.mapper;

import com.shareplatform.demo.domain.EsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EsProductMapper {
    /**
     *获取指定id的搜索商品
     * @param id
     * @return
     */
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}

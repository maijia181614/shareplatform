package com.shareplatform.demo.mapper;

import com.shareplatform.demo.entity.Brand;
import com.shareplatform.demo.entity.IndexConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mjq
 * @since 2021-06-07
 */
public interface IndexConfigMapper extends BaseMapper<IndexConfig> {
    List<Brand> getRecommendBrandList(@Param("offset") Integer offset, @Param("limit") Integer limit);
}

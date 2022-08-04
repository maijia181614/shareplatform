package com.shareplatform.demo.domain;

import com.shareplatform.demo.entity.Brand;
import com.shareplatform.demo.entity.HomeAdvertise;
import com.shareplatform.demo.entity.IndexConfig;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *首页内容返回信息封装
 */
@Getter
@Setter
public class HomeContentResult {
    @ApiModelProperty(value = "轮播广告")
    private List<HomeAdvertise> homeAdvertiseList;
    @ApiModelProperty(value = "推荐品牌")
    private List<Brand> brandList;
    @ApiModelProperty(value = "推荐商品")
    private List<IndexConfig> indexConfigList;
}

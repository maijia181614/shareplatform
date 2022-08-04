package com.shareplatform.demo.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author mjq
 * @since 2021-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Goods对象", description="")
public class Goods implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "物品ID")
    @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goodsId;

    @ApiModelProperty(value = "物品名称")
    private String goodsName;

    @ApiModelProperty(value = "物品简介")
    private String goodsIntro;

    @ApiModelProperty(value = "物品分类id")
    private Integer goodsCategoryId;

    @ApiModelProperty(value = "物品主图片")
    private String goodsCoverImg;

    @ApiModelProperty(value = "物品轮播图片")
    private String goodsCarousel;

    @ApiModelProperty(value = "物品详情")
    private String goodsDetailContent;

    @ApiModelProperty(value = "物品价格")
    private double goodsPrice;

    @ApiModelProperty(value = "物品数量")
    private Integer stockNum;

    @ApiModelProperty(value = "物品上架状态（0下架，1上架）")
    private Integer goodsStatus;

    @ApiModelProperty(value = "物品评价分数（满分5）")
    private double goodsScore;

}

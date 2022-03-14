package com.shareplatform.demo.entity;

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
 * @since 2021-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Entrust对象", description="")
public class Entrust implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "委托id")
    @TableId(value = "entrust_id", type = IdType.AUTO)
    private Integer entrustId;

    @ApiModelProperty(value = "委托物品名称")
    private String eGoodsName;

    @ApiModelProperty(value = "物品简介")
    private String eGoodsIntro;

    @ApiModelProperty(value = "物品分类id")
    private Integer eGoodsCategory;

    @ApiModelProperty(value = "物品主图片")
    private String eGoodsCoverImg;

    @ApiModelProperty(value = "物品轮播图片")
    private String eGoodsCarousel;

    @ApiModelProperty(value = "物品详情")
    private String eGoodsDetailContent;

    @ApiModelProperty(value = "委托租赁单价 元/天")
    private Double eGoodsPrice;

    @ApiModelProperty(value = "委托数量")
    private Integer eStockNum;


}

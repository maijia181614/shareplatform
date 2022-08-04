package com.shareplatform.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value="CartItem对象", description="")
public class CartItem implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "购物项id")
    @TableId(value = "cart_item_id", type = IdType.AUTO)
    private Integer cartItemId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "物品id")
    private Integer goodsId;

    @ApiModelProperty(value = "物品数量")
    private Integer goodsCount;

    @ApiModelProperty(value = "删除标识字段(0-未删除 1-已删除)")
    private Integer isDeleted;

    @ApiModelProperty(value = "开始时间")
    private String createTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "单项价")
    private double price;

    @ApiModelProperty(value = "物品图片")
    private String goodsCoverImg;

    @ApiModelProperty(value = "物品名称")
    private String goodsName;

    @ApiModelProperty(value = "总租赁天数")
    @TableField(exist = false)
    private Integer totalDay;

    @ApiModelProperty(value = "单项总价")
    @TableField(exist = false)
    private double singleTotalPrice;

}

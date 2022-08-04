package com.shareplatform.demo.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
@ApiModel(value="OrderItem对象", description="")
public class OrderItem implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "订单项id")
    @TableId(value = "order_item_id", type = IdType.AUTO)
    private Integer orderItemId;

    @ApiModelProperty(value = "关联订单id")
    private Integer orderId;

    @ApiModelProperty(value = "关联物品id")
    private Integer goodsId;

    @ApiModelProperty(value = "物品名称")
    private String goodsName;

    @ApiModelProperty(value = "物品主图")
    private String goodsCoverImg;

    @ApiModelProperty(value = "物品价格")
    private Double price;

    @ApiModelProperty(value = "物品数量")
    private Integer goodsCount;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "结束租赁时间")
    private String endTime;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

}

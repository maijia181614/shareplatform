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
 * @since 2021-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="IndexConfig对象", description="")
public class IndexConfig implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "首页配置项id")
    @TableId(value = "config_id", type = IdType.AUTO)
    private Integer configId;

    @ApiModelProperty(value = "显示名称")
    private String configName;

    @ApiModelProperty(value = "1-今日推进 2-今日优惠 3-为您推荐")
    private Integer configType;

    @ApiModelProperty(value = "物品id")
    private Integer goodsId;

    @ApiModelProperty(value = "删除标识字段(0-未删除 1-已删除)")
    private Integer isDeleted;

    @ApiModelProperty(value = "物品图片")
    private String goodsCoverImg;


}

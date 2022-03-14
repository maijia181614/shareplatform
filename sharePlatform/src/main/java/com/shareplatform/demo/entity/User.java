package com.shareplatform.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.ArrayList;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

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
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户主键id")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "用户昵称")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "登录用户名")
    @TableField("userName")
    private String userName;

    @ApiModelProperty(value = "MD5加密后的密码")
    @TableField("passWord")
    private String passWord;

    @ApiModelProperty(value = "收货地址")
    private String address;

    @ApiModelProperty(value = "注册时间")
    @TableField("createTime")
    private String createTime;

    @ApiModelProperty(value = "加密盐")
    @TableField("createTime")
    private String salt;
    /**
     * 角色集合
     */
    @ApiModelProperty(value = "角色集合")
    @TableField(exist = false) //非数据库字段
    private List<Role> roleList = new ArrayList<>();

}

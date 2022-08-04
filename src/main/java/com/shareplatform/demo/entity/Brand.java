package com.shareplatform.demo.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class Brand implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "品牌id")
    private Long id;
    @ApiModelProperty(value = "品牌名称")
    private String name;
    @ApiModelProperty(value = "品牌显示状态")
    private Integer showStatus;
    @ApiModelProperty(value = "品牌logo")
    private String logo;
    @ApiModelProperty(value = "品牌故事")
    private String brandStory;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBrandStory() {
        return brandStory;
    }

    public void setBrandStory(String brandStory) {
        this.brandStory = brandStory;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", showStatus=" + showStatus +
                ", logo='" + logo + '\'' +
                ", brandStory='" + brandStory + '\'' +
                '}';
    }
}

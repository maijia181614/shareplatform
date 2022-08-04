package com.shareplatform.demo.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 首页轮播广告类
 */
public class HomeAdvertise implements Serializable {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "广告id")
    private Long id;
    @ApiModelProperty(value = "广告名称")
    private String name;
    @ApiModelProperty(value = "广告类型")
    private Integer type;
    @ApiModelProperty(value = "图片")
    private String pic;
    @ApiModelProperty(value = "生效时间")
    private Date startTime;
    @ApiModelProperty(value = "失效时间")
    private Date endTime;
    @ApiModelProperty(value = "上下线状态：0->下线/1->上线")
    private Integer status;
    @ApiModelProperty(value = "点击数")
    private Integer clickCount;
    @ApiModelProperty(value = "链接地址")
    private String url;
    @ApiModelProperty(value = "备注")
    private String note;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "HomeAdvertise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", pic='" + pic + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", clickCount=" + clickCount +
                ", url='" + url + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}

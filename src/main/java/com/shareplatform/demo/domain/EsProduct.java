package com.shareplatform.demo.domain;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 搜索中的商品信息
 */


@Getter
@Setter
@ApiModel(value = "Es搜索商品对象")
@Document(indexName = "shareplatform")
@Setting(shards = 1, replicas = 0)
public class EsProduct implements Serializable {
    private static final long serialVersionUID = -1L;

    @Id
    private Long id;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String productName;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String productIntro;
    @Field(type = FieldType.Keyword)
    private Integer productCategoryId;
    @Field(type = FieldType.Keyword)
    private String productCoverImg;
    @Field(type = FieldType.Keyword)
    private String productCarousel;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String productDetailContent;

    private BigDecimal productPrice;

    private Integer stockNum;

    private Integer productStatus;

    private Double productScore;
}

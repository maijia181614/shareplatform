package com.shareplatform.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document
@Getter
@Setter
public class UserReadHistory {
    @Id
    private String id;
    @Indexed
    private Integer userId;
    private String userName;
    @Indexed
    private Integer productId;
    private String productName;
    private String productCoverImg;
    private String productIntro;
    private BigDecimal productPrice;
    private Date createTime;

}

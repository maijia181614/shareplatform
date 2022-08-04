package com.shareplatform.demo.service.impl;

import com.shareplatform.demo.entity.Goods;
import com.shareplatform.demo.entity.UserReadHistory;
import com.shareplatform.demo.repository.UserReadHistoryrRepository;
import com.shareplatform.demo.service.GoodsService;
import com.shareplatform.demo.service.UserReadHistoryService;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserReadHistoryServiceImpl implements UserReadHistoryService {
    private final Logger logger= LoggerFactory.getLogger(EsProductServiceImpl.class);

    @Autowired
    private UserReadHistoryrRepository userReadHistoryrRepository;

    @Autowired
    private GoodsService goodsService;

    @Override
    public int create(UserReadHistory userReadHistory) {
        Integer productId = userReadHistory.getProductId();
        Goods goods = goodsService.getById(productId);
        userReadHistory.setProductId(goods.getGoodsId());
        userReadHistory.setProductCoverImg(goods.getGoodsCoverImg());
        userReadHistory.setProductIntro(goods.getGoodsIntro());
        userReadHistory.setProductName(goods.getGoodsName());
        userReadHistory.setProductPrice(BigDecimal.valueOf(goods.getGoodsPrice()));
        userReadHistory.setId(null);
        userReadHistory.setCreateTime(new Date());
        logger.info(userReadHistory.toString());
        userReadHistoryrRepository.save(userReadHistory);
        return 1;
    }

    @Override
    public int delete(List<String> ids) {
        List<UserReadHistory> deleteList = new ArrayList<>();
        for(String id : ids){
            UserReadHistory userReadHistory = new UserReadHistory();
            userReadHistory.setId(id);
            deleteList.add(userReadHistory);
        }
        userReadHistoryrRepository.deleteAll(deleteList);
        return ids.size();
    }

    @Override
    public Page<UserReadHistory> list(Integer userId, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum-1, pageSize);
        return userReadHistoryrRepository.findByUserIdOrderByCreateTime(userId, pageable);
    }
}

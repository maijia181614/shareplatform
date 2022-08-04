package com.shareplatform.demo.repository;

import com.shareplatform.demo.entity.UserReadHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserReadHistoryrRepository extends MongoRepository<UserReadHistory,String> {
    /**
     * 根据用户id按时间顺序获取浏览记录
     * @param userId
     * @return
     */
    Page<UserReadHistory> findByUserIdOrderByCreateTime(Integer userId, Pageable pageable);
}

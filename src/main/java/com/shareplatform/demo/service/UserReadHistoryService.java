package com.shareplatform.demo.service;

import com.shareplatform.demo.entity.UserReadHistory;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserReadHistoryService {
    /**
     * 创建浏览记录
     * @param userReadHistory
     * @return
     */
    int create(UserReadHistory userReadHistory);

    /**
     * 根据id批量删除浏览记录
     * @param ids
     * @return
     */
    int delete(List<String> ids);

    /**
     * 获取用户浏览记录
     * @param userId
     * @return
     */
    Page<UserReadHistory> list(Integer userId, Integer pageNum, Integer pageSize);



}

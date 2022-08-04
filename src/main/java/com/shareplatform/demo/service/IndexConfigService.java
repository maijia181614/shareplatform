package com.shareplatform.demo.service;

import com.shareplatform.demo.domain.HomeContentResult;
import com.shareplatform.demo.entity.IndexConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.PrivateKey;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mjq
 * @since 2021-06-07
 */
public interface IndexConfigService extends IService<IndexConfig> {

    public HomeContentResult content();
}

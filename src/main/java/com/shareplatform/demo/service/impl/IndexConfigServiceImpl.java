package com.shareplatform.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shareplatform.demo.domain.HomeContentResult;
import com.shareplatform.demo.entity.HomeAdvertise;
import com.shareplatform.demo.entity.IndexConfig;
import com.shareplatform.demo.mapper.HomeAdvertiseMapper;
import com.shareplatform.demo.mapper.IndexConfigMapper;
import com.shareplatform.demo.service.IndexConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mjq
 * @since 2021-06-07
 */
@Service
public class IndexConfigServiceImpl extends ServiceImpl<IndexConfigMapper, IndexConfig> implements IndexConfigService {

    @Autowired
    private IndexConfigMapper indexConfigMapper;

    @Autowired
    private HomeAdvertiseMapper homeAdvertiseMapper;

    /**
     * 获取首页内容
     * @return
     */
    @Override
    public HomeContentResult content() {
        HomeContentResult homeContentResult = new HomeContentResult();
        homeContentResult.setBrandList(indexConfigMapper.getRecommendBrandList(0,6));
        homeContentResult.setHomeAdvertiseList(getHomeAdvertiseList());
        return homeContentResult;
    }

    private List<HomeAdvertise> getHomeAdvertiseList(){
        QueryWrapper<HomeAdvertise> homeAdvertiseQueryWrapper = new QueryWrapper<>();
        homeAdvertiseQueryWrapper.eq("type",1).eq("status",1);
        List<HomeAdvertise> homeAdvertiseList = homeAdvertiseMapper.selectList(homeAdvertiseQueryWrapper);
        return homeAdvertiseList;
    }
}

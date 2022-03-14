package com.shareplatform.demo.service.impl;

import com.shareplatform.demo.entity.Category;
import com.shareplatform.demo.mapper.CategoryMapper;
import com.shareplatform.demo.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mjq
 * @since 2021-06-07
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}

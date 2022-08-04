package com.shareplatform.demo.service.impl;

import com.shareplatform.demo.entity.Order;
import com.shareplatform.demo.mapper.OrderMapper;
import com.shareplatform.demo.service.OrderService;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}

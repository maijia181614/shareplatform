package com.shareplatform.demo.service.impl;

import com.shareplatform.demo.entity.OrderItem;
import com.shareplatform.demo.mapper.OrderItemMapper;
import com.shareplatform.demo.service.OrderItemService;
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
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}

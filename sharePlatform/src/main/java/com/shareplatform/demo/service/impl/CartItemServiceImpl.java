package com.shareplatform.demo.service.impl;

import com.shareplatform.demo.entity.CartItem;
import com.shareplatform.demo.mapper.CartItemMapper;
import com.shareplatform.demo.service.CartItemService;
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
public class CartItemServiceImpl extends ServiceImpl<CartItemMapper, CartItem> implements CartItemService {

}

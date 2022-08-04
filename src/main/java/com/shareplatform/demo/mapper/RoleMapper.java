package com.shareplatform.demo.mapper;

import com.shareplatform.demo.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mjq
 * @since 2021-06-10
 */
public interface RoleMapper extends BaseMapper<Role> {
    public List<Role> listRoleCascadeByUser(Integer userId);
}

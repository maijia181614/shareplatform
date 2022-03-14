package com.shareplatform.demo.mapper;

import com.shareplatform.demo.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mjq
 * @since 2021-06-10
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    public Permission listPermissionCascadeByRole(Integer roleId);

}

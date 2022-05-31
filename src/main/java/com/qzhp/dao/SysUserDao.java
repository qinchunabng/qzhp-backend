package com.qzhp.dao;

import com.qzhp.entity.po.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author mrqin
 * @since 2022-05-29
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUser> {

}

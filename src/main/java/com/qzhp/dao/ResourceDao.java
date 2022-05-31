package com.qzhp.dao;

import com.qzhp.entity.po.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mrqin
 * @since 2022-05-29
 */
@Mapper
public interface ResourceDao extends BaseMapper<Resource> {


    List<Resource> getListByUserId(@Param("userId") Long userId);
}

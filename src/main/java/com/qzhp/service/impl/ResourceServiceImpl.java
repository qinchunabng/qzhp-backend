package com.qzhp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qzhp.common.DelFlag;
import com.qzhp.entity.po.Resource;
import com.qzhp.dao.ResourceDao;
import com.qzhp.service.IResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mrqin
 * @since 2022-05-29
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceDao, Resource> implements IResourceService {

    @Override
    public List<Resource> getPermissionList(Long userId) {
        return this.baseMapper.getListByUserId(userId);
    }

    /**
     * 获取所有有效的资源
     * @return
     */
    public List<Resource> listAll(){
        QueryWrapper<Resource> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Resource::getDelFlag, DelFlag.NOT_DELETED.getCode());
        return this.baseMapper.selectList(queryWrapper);
    }
}

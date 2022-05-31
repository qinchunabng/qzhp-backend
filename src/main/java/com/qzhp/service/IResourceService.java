package com.qzhp.service;

import com.qzhp.entity.po.Resource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mrqin
 * @since 2022-05-29
 */
public interface IResourceService extends IService<Resource> {


    /**
     * 获取用户权限集合
     * @param userId
     * @return
     */
    List<Resource> getPermissionList(Long userId);

    /**
     * 获取所有有效的资源
     * @return
     */
    List<Resource> listAll();
}

package com.qzhp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qzhp.entity.dto.UserInfo;
import com.qzhp.entity.po.Resource;
import com.qzhp.entity.po.SysUser;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author mrqin
 * @since 2022-05-21
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 通过username获取用户信息
     * @param username
     * @return
     */
    SysUser getByUsername(String username);

    /**
     * 通过用户ID获取用户权限
     * @param userId
     * @return
     */
    List<Resource> getPermissionList(Long userId);

    /**
     * 注册用户
     * @param userInfo
     * @return
     */
    SysUser register(UserInfo userInfo);

    /**
     *用户登陆操作
     * @param username
     * @param password
     * @return jwt token
     */
    String login(String username, String password);
}

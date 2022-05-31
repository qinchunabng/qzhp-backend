package com.qzhp.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.qzhp.common.UserStatus;
import com.qzhp.dao.SysUserDao;
import com.qzhp.entity.dto.UserInfo;
import com.qzhp.entity.po.Resource;
import com.qzhp.entity.po.SysUser;
import com.qzhp.exception.BusinessException;
import com.qzhp.service.IResourceService;
import com.qzhp.service.ISysUserService;
import com.qzhp.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author mrqin
 * @since 2022-05-21
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements ISysUserService {

    private final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 通过username获取用户信息
     * @param username
     * @return
     */
    @Override
    public SysUser getByUsername(String username){
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername, username);
        return getOne(queryWrapper);
    }

    /**
     * 通过用户ID获取用户权限
     * @param userId
     * @return
     */
    @Override
    public List<Resource> getPermissionList(Long userId){
        if(userId == null){
            return Lists.newArrayList();
        }
        return resourceService.getPermissionList(userId);
    }

    /**
     * 注册用户
     * @param userInfo
     * @return
     */
    @Transactional(rollbackFor = { Exception.class })
    @Override
    public SysUser register(UserInfo userInfo){
        logger.info("###### 注册用户, userInfo: {}", JSONUtil.toJsonStr(userInfo));
        if(getByUsername(userInfo.getUsername()) != null){
            throw new BusinessException("该用户已存在");
        }
        SysUser sysUser = new SysUser();
        sysUser.setUsername(userInfo.getUsername());
        sysUser.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        sysUser.setIcon(userInfo.getIcon());
        sysUser.setNickname(userInfo.getNickname());
        sysUser.setNote(userInfo.getNote());
        sysUser.setCreateTime(LocalDateTime.now());
        sysUser.setStatus(UserStatus.ENABLE.getCode());
        save(sysUser);
        return sysUser;
    }

    /**
     *用户登陆操作
     * @param username
     * @param password
     * @return jwt token
     */
    @Override
    public String login(String username, String password){
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        //验证密码是否正确
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BusinessException("密码不正确");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenUtil.generateToken(userDetails);
    }
}

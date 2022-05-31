package com.qzhp.controller;

import com.qzhp.common.CommonResult;
import com.qzhp.common.OperationGroup;
import com.qzhp.entity.dto.UserInfo;
import com.qzhp.entity.dto.UserLogin;
import com.qzhp.entity.po.Resource;
import com.qzhp.entity.po.SysUser;
import com.qzhp.service.IResourceService;
import com.qzhp.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author mrqin
 * @since 2022-05-21
 */
@Api(tags = "AdminController", value = "后台用户管理")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IResourceService resourceService;

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public CommonResult<SysUser> register(@RequestBody @Validated(OperationGroup.Add.class) UserInfo userInfo){
        SysUser sysUser = sysUserService.register(userInfo);
        if(sysUser == null){
            return CommonResult.failed();
        }
        return CommonResult.success(sysUser);
    }

    @ApiOperation(value = "用户登陆")
    @PostMapping("/login")
    public CommonResult login(@RequestBody @Valid UserLogin userLogin){
        String token = sysUserService.login(userLogin.getUsername(), userLogin.getPassword());
        return CommonResult.success(token);
    }

    @ApiOperation("获取用户所有的权限")
    @GetMapping("/permission/{userId}")
    public CommonResult<List<Resource>> getPermissionList(@PathVariable Long userId){
        List<Resource> permissionList = resourceService.getPermissionList(userId);
        return CommonResult.success(permissionList);
    }
}

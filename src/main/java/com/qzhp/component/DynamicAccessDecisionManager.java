package com.qzhp.component;

import cn.hutool.core.collection.CollUtil;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 动态权限决策管理器，用户判断用户是否有访问权限
 *
 * @author qcb
 * @date 2022/05/29 16:46.
 */
public class DynamicAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        if(CollUtil.isEmpty(collection)){
            return;
        }
        for(ConfigAttribute configAttribute : collection){
            String needAuthority = configAttribute.getAttribute();
            for(GrantedAuthority grantedAuthority : authentication.getAuthorities()){
                if(needAuthority.trim().equals(grantedAuthority.getAuthority())){
                    return;
                }
            }
        }
        throw new AccessDeniedException("抱歉，您没有访问权限");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

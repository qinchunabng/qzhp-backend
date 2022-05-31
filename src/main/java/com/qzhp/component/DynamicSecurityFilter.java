package com.qzhp.component;

import com.qzhp.config.IgnoreUrlsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 动态权限过滤器
 *
 * @author qcb
 * @date 2022/05/29 16:17.
 */
public class DynamicSecurityFilter extends AbstractSecurityInterceptor implements Filter {

    @Autowired
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Autowired
    public void setMyAccessDecisionManager(DynamicAccessDecisionManager dynamicAccessDecisionManager){
        super.setAccessDecisionManager(dynamicAccessDecisionManager);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        FilterInvocation invocation = new FilterInvocation(servletRequest, servletResponse, filterChain);
        //OPTIONS请求放行
        if(request.getMethod().equals(HttpMethod.OPTIONS.toString())){
            invocation.getChain().doFilter(invocation.getRequest(), invocation.getResponse());
            return;
        }
        //白名单请求放行
        PathMatcher pathMatcher = new AntPathMatcher();
        for(String path : ignoreUrlsConfig.getUrls()){
            if(pathMatcher.match(path, request.getRequestURI())){
                invocation.getChain().doFilter(invocation.getRequest(), invocation.getResponse());
                return;
            }
        }
        //此处调用AccessDecisionManager中decide进行鉴权
        InterceptorStatusToken token = super.beforeInvocation(invocation);
        try {
            invocation.getChain().doFilter(invocation.getRequest(), invocation.getResponse());
        }finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return dynamicSecurityMetadataSource;
    }
}

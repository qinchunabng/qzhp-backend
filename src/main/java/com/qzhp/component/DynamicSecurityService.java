package com.qzhp.component;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * 动态权限相关业务类
 *
 * @author qcb
 * @date 2022/05/29 16:21.
 */
public interface DynamicSecurityService {

    /**
     *加载ANT通配符和资源对应的MAP
     * @return
     */
    Map<String, ConfigAttribute> loadDataSource();
}

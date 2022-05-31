package com.qzhp.service.impl;

import com.qzhp.entity.po.Menu;
import com.qzhp.dao.MenuDao;
import com.qzhp.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mrqin
 * @since 2022-05-29
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements IMenuService {

}

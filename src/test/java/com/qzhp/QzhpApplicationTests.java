package com.qzhp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qzhp.dao.SysUserDao;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = QzhpApplication.class)
@RunWith(SpringRunner.class)
class QzhpApplicationTests {

	@Autowired
	private SysUserDao sysUserDao;

	@Test
	void testDao() {
		sysUserDao.selectList(new QueryWrapper<>());

	}

}

package com.test.config;

import java.util.List;

import org.junit.Test;

import com.jt.sys.dao.SysConfigDao;
import com.jt.sys.entity.SysConfig;
import com.test.TestBase;

public class TestSysConfigDao extends TestBase {

	 @Test
	 public void testFindPageObjects(){
		 SysConfigDao dao=
		 ctx.getBean(SysConfigDao.class);
		 List<SysConfig> list=
		 dao.findPageObjects("o",
				 1, 
				 2);
		 System.out.println(list);
	 }
}

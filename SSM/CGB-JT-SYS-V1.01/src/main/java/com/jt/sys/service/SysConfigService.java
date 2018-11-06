package com.jt.sys.service;

import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysConfig;

public interface SysConfigService {
	
  /**
	 * 将配置信息更新到数据库
	 * @param entity
	 * @return
	 */
  int updateObject(SysConfig entity);
  
  /**
   * 将配置信息写入到数据库
   * @param entity
   * @return
   */
  int saveObject(SysConfig entity);

  /**
   * 基于配置id执行删除业务
   * @param ids 此值来自页面(选中的多条记录的id值)
   * @return
   */
  int deleteObjects(Integer... ids);
	
  /***
   * 依据分页查询配置信息
   * @param name 
   * @param pageCurrent
   * @return
   */
  PageObject<SysConfig> findPageObjects(
		  String name,Integer pageCurrent);
}






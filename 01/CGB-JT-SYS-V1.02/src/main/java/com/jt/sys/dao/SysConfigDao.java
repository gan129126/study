package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.sys.entity.SysConfig;

public interface SysConfigDao {
	/***
	 * 将对象更新(映射)到数据库
	 * @param entity
	 * @return
	 */
	int updateObject(SysConfig entity);
	
	 /***
	  * 将对象持久化(映射)到数据库
	  * @param entity
	  * @return
	  */
	 int insertObject(SysConfig entity);
	
	 /***
	  * 基于配置id执行删除操作
	  * @param ids
	  * @return
	  */
	 int deleteObjects(
			 @Param("ids")Integer... id);
	
     /**
      * 查询当前页数据
      * @param name  查询条件
      * @param startIndex 当前页数据的起始位置
      * @param pageSize 页面大小
      * @return
      */
	 List<SysConfig> findPageObjects(
			 @Param("name")String name,
			 @Param("startIndex")Integer startIndex,
			 @Param("pageSize")Integer pageSize);
	 
	 /**
	  * 依据条件统计总记录数
	  * @param name
	  * @return
	  */
	 int getRowCount(@Param("name")String name);
}

package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.common.vo.CheckBox;
import com.jt.sys.entity.SysRole;

/**
 * 负责角色模块数据的ORM操作
 * @author 速度
 */
public interface SysRoleDao {
	
	/**
	 * 获取所有角色信息
	 * @return
	 */
	List<CheckBox> findObjects();
	
	
	/**
	 * 更新角色自身信息
	 * @param entity
	 * @return
	 */
	int updateObject(SysRole entity);
	 /**
	  * 基于角色获取角色信息
	  * @param id
	  * @return
	  */
	 SysRole findObjectById(Integer id);
	
	 /**
	  * 保存角色自身信息
	  * @param entity
	  * @return
	  */
	 int insertObject(SysRole entity);
	 
	
	 /**
	  * 基于id删除角色自身信息
	  * @param id
	  * @return
	  */
	 int deleteObject(Integer id);
	 
      /**
       * 分页查询角色信息
       * @param name 查询条件:角色名
       * @param startIndex 当前页的起始位置
       * @param pageSize 页面大小
       * @return 返回当前页获取的记录
       */
	  List<SysRole> findPageObjects(
			  @Param("name")String name,
			  @Param("startIndex")Integer startIndex,
			  @Param("pageSize")Integer pageSize);
	  
	  int getRowCount(@Param("name")String name);
}







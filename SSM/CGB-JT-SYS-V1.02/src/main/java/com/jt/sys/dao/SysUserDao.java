package com.jt.sys.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.sys.entity.SysUser;
import com.jt.sys.vo.SysUserDeptResult;

public interface SysUserDao {
	/**
	 * 基于用户名查找用户对象
	 * @param username
	 * @return
	 */
	SysUser findUserByUserName(String username);
	
	/**
	 * 更新用户自身信息
	 * @param entity
	 * @return
	 */
	int updateObject(SysUser entity);
	
	/**
	 * 基于id查询用户信息
	 * @param id
	 * @return
	 */
	SysUserDeptResult findObjectById(Integer id);
	
	/**
	 * 负责将用户信息写入到数据库
	 * @param entity
	 * @return
	 */
	int insertObject(SysUser entity);


	/**
	 * 基于用户id启用或禁用用户状态 
	 * @param id
	 * @param valid (状态标识,1表示启用,0表示禁用)
	 * @param modifiedUser (是谁执行的禁用或启用操作)
	 * @return
	 */
	int validById(
			@Param("id")Integer id,
			@Param("valid")Integer valid,
			@Param("modifiedUser")String modifiedUser);
	 
	 /**
	  * 依据条件分页查询的用户以及部分信息
	  * @param username 用户名
	  * @param startIndex 当前页的起始位置
	  * @param pageSize 页面大小
	  * @return
	  */
	 List<SysUserDeptResult> findPageObjects(
			 @Param("username")String username,
			 @Param("startIndex")Integer startIndex,
			 @Param("pageSize")Integer pageSize);
	 
	 /**
	  * 依据查询条件统计总记录数
	  * @param username
	  * @return
	  * mybatis 拦截器在分页查询中应用?
	  */
	 int getRowCount(@Param("username")String username);
    
}










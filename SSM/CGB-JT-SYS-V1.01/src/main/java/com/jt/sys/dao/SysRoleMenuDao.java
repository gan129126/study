package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 为什么写此DAO?
 * 
 * 市场规则:
 * 1)一个表对应一个映射文件
 * 2)一个映射文件对应一个DAO接口
 */
public interface SysRoleMenuDao {
	
	
	
	/**
	 * 基于角色id从中间表(角色菜单的关系表)
	 * 获取菜单id
	 * @param id
	 * @return
	 */
	List<Integer> findMenuIdsByRoleId(
			Integer id);
	
	
	/**
	 * 角色与菜单是many2many的关系,这种关系
	 * 的维护,在表设计领域中是通过中间表来实现的.
	 * 
	 * 保存角色和菜单的关系数据
	 * @param roleId 角色Id
	 * @param menuIds 菜单id
	 * @return
	 */
	int insertObject(
			@Param("roleId")Integer roleId,
			@Param("menuIds")Integer[] menuIds);
	
	
	/**基于菜单ID删除角色菜单关系表数据*/
	int deleteObjectsByMenuId(Integer menuId);
	
	/**基于角色Id删除角色菜单关系数据*/
    int deleteObjectsByRoleId(Integer roleId);
	
}






package com.jt.sys.service;
import java.util.List;
import java.util.Map;

import com.jt.common.vo.CheckBox;
import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysRole;
public interface SysRoleService {
	
	 /**
	  * 获取所有的角色信息
	  * @return
	  */
	 List<CheckBox> findObjects();
	
	 int updateObject(SysRole entity,
			 Integer[]menuIds);
	
	 /**
	  * 基于角色id获取角色以及对应的菜单id
	  * @param id
	  * @return
	  */
	 Map<String,Object> findObjectById(Integer id);
	
	 /**
	  * 保存角色以及角色与菜单的关系数据
	  * @param entity
	  * @param menuIds
	  * @return
	  */
	 int saveObject(SysRole entity,Integer[] menuIds);
	
	  /**
	   * 删除角色信息
	   * @param id
	   * @return
	   */
	  int deleteObject(Integer id);
	
	  /**
	   * 分页查询当前页记录以及总记录数
	   * @param name
	   * @param pageCurrent 当前页页码
	   * @return
	   */
	  PageObject<SysRole> findPageObjects(
			  String name,
			  Integer pageCurrent);
}







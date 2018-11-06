package com.jt.sys.service;

import java.util.List;
import java.util.Map;

import com.jt.common.vo.Node;
import com.jt.sys.entity.SysMenu;

public interface SysMenuService {
	
	int updateObject(SysMenu entity);
	
	int saveObject(SysMenu entity);

    /**
     * 查询菜单节点信息
     * @return
     */
	public List<Node> findZtreeMenuNodes();

	/**
	 * 查询所有菜单以及上级菜单信息
	 * @return
	 */
	List<Map<String,Object>> findObjects();
	/**
	 * 基于菜单id执行菜单的删除业务
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
}








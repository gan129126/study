package com.jt.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jt.common.exception.ServiceException;
import com.jt.common.vo.Node;
import com.jt.sys.dao.SysMenuDao;
import com.jt.sys.dao.SysRoleMenuDao;
import com.jt.sys.entity.SysMenu;
import com.jt.sys.service.SysMenuService;
@Service
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	private SysMenuDao sysMenuDao;
	@Autowired
    private SysRoleMenuDao sysRoleMenuDao;
	
	@Override
	public int updateObject(SysMenu entity) {
		//1.参数有效性验证
		if(entity==null)
		throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
		throw new IllegalArgumentException("菜单名不能为空");
		//2.保存菜单对象到数据库
		int rows=sysMenuDao.updateObject(entity);
		//3.验证结果
		if(rows==0)
		throw new ServiceException("记录可能已经不存在");
		//4.返回结果
		return rows;
	}
	@Override
	public int saveObject(SysMenu entity) {
		//1.参数有效性验证
		if(entity==null)
		throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
		throw new IllegalArgumentException("菜单名不能为空");
		//2.保存菜单对象到数据库
		int rows=sysMenuDao.insertObject(entity);
		//3.验证结果
		if(rows==0)
		throw new ServiceException("save error");
		//4.返回结果
		return rows;
	}
	
	@Override
	public List<Node> findZtreeMenuNodes() {
		return sysMenuDao.findZtreeMenuNodes();
	}
	
	@Override
	public int deleteObject(Integer id) {
		//1.参数有效性验证
		if(id==null||id<1)
		throw new IllegalArgumentException("id值无效");
		//2.基于菜单id统计子菜单的个数,并进行业务验证.
		int count=sysMenuDao.getChildCount(id);
		if(count>0)
		throw new ServiceException("请先删除子菜单");
		//3.删除菜单自身信息
		int rows=sysMenuDao.deleteObject(id);
		if(rows==0)
		throw new ServiceException("记录可能已经不存在");
		//4.删除菜单与角色关系数据.
		sysRoleMenuDao.deleteObjectsByMenuId(id);
		//5.返回删除的行数
		return rows;
	}
	
	@Override
	public List<Map<String, Object>> findObjects() {
		return sysMenuDao.findObjects();
	}

}

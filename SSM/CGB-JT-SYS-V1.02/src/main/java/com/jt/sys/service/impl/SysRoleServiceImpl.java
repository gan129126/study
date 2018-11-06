package com.jt.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jt.common.exception.ServiceException;
import com.jt.common.vo.CheckBox;
import com.jt.common.vo.PageObject;
import com.jt.sys.dao.SysRoleDao;
import com.jt.sys.dao.SysRoleMenuDao;
import com.jt.sys.dao.SysUserRoleDao;
import com.jt.sys.entity.SysRole;
import com.jt.sys.service.SysRoleService;
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
	private SysRoleDao sysRoleDao;
    
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;
    
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    
    @Override
    public List<CheckBox> findObjects() {
    	return sysRoleDao.findObjects();
    }
    
    @Override
    public int updateObject(SysRole entity, Integer[] menuIds) {
        //1.参数有效性验证
    	if(entity==null) 
    	throw new IllegalArgumentException("保存对象不能为空");
    	if(StringUtils.isEmpty(entity.getName()))
    	throw new IllegalArgumentException("角色不能为空");
    	if(menuIds==null||menuIds.length==0)
    	throw new IllegalArgumentException("至少要角色分配一个权限");
    	//2.更新角色自身信息
    	int rows=sysRoleDao.updateObject(entity);
    	if(rows==0)
    	throw new ServiceException("记录可能已经不存在");
    	//3.删除角色菜单关系数据
    	sysRoleMenuDao.deleteObjectsByRoleId(entity.getId());
    	//4.重新插入新的关系数据
    	sysRoleMenuDao.insertObject(entity.getId(),
    			menuIds);
    	//5.返回更新结果
    	return rows;
    }
    
    @Override
    public Map<String, Object> findObjectById(Integer id) {
    	//1.参数的有效性验证
    	if(id==null||id<1)
    	throw new IllegalArgumentException("角色id不合法");
    	//2.查询角色自身信息
    	SysRole role=sysRoleDao.findObjectById(id);
    	if(role==null)
    	throw new ServiceException("记录可能已经不存在");
    	//3.查询角色对应的菜单id
    	List<Integer> menuIds=
    	sysRoleMenuDao.findMenuIdsByRoleId(id);
    	//4.封装查询结果
    	Map<String,Object> map=new HashMap<>();
    	map.put("role", role);
    	map.put("menuIds",menuIds);
    	//5.返回结果
    	return map;
    }
    
    @Override
    public int saveObject(SysRole entity, 
    		Integer[] menuIds) {
    	//1.参数有效性验证
    	if(entity==null)
    	throw new IllegalArgumentException("保存对象不能为空");
    	if(StringUtils.isEmpty(entity.getName()))
    	throw new IllegalArgumentException("角色名不能为空");
    	if(menuIds==null||menuIds.length==0)
    	throw new IllegalArgumentException("必须为角色指定权限");
    	//2.保存角色自身信息
    	int rows=sysRoleDao.insertObject(entity);
    	//3.保存角色与菜单的关系数据
        sysRoleMenuDao.insertObject(entity.getId(),
        		menuIds);
    	//4.返回结果
    	return rows;
    }
    
    @Override
    public int deleteObject(Integer id) {
    	//1.验证参数有效性
    	if(id==null||id<1)
    	throw new IllegalArgumentException("id值无效");
    	//2.执行删除操作
    	int rows=sysRoleDao.deleteObject(id);
    	if(rows==0)
    	throw new ServiceException("此记录可能已经存在");
    	sysRoleMenuDao.deleteObjectsByRoleId(id);
    	sysUserRoleDao.deleteObjectsByRoleId(id);
    	//3.返回结果
    	return rows;
    }
    
	@Override
	public PageObject<SysRole> findPageObjects(
			String name, Integer pageCurrent) {
		//1.验证参数有效性(pageCurrent)
		if(pageCurrent==null||pageCurrent<1)
		throw new IllegalArgumentException("页码值不正确");
		//2.基于name参数查询总记录数,并进行验证.
		int rowCount=sysRoleDao.getRowCount(name);
		if(rowCount==0)
		throw new ServiceException("没有查对应记录");
		//3.基于条件name,pageCurrent查询当前页记录
		int pageSize=2;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysRole> records=
		sysRoleDao.findPageObjects(name,
				startIndex, pageSize);
		//4.将两次查询结果进行封装
		PageObject<SysRole> po=new PageObject<>();
		po.setRowCount(rowCount);
		po.setRecords(records);
		po.setPageSize(pageSize);
		po.setPageCurrent(pageCurrent);
		po.setPageCount((rowCount-1)/pageSize+1);
		//5.返回封装结果.
		return po;
	}

}





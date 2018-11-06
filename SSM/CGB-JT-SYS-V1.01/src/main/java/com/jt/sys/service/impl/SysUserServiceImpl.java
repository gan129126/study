package com.jt.sys.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jt.common.exception.ServiceException;
import com.jt.common.vo.PageObject;
import com.jt.sys.dao.SysUserDao;
import com.jt.sys.dao.SysUserRoleDao;
import com.jt.sys.entity.SysUser;
import com.jt.sys.service.SysUserService;
import com.jt.sys.vo.SysUserDeptResult;
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
	private SysUserDao sysUserDao;
    
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    
    @Override
    public Map<String, Object> findObjectById(
    		Integer userId) {
    	//1.参数有效性验证
    	if(userId==null||userId<1)
    	throw new IllegalArgumentException("用户id无效");
    	//2.基于用户id查询用户自身信息
    	SysUserDeptResult result=
    	sysUserDao.findObjectById(userId);
    	if(result==null)
    	throw new ServiceException("记录可能已经不存在");
    	//3.基于用户id查询角色id
    	List<Integer> roleIds=
    	sysUserRoleDao.findRoleIdsByUserId(userId);
    	//4.封装查询结果并返回
    	Map<String,Object> map=new HashMap<>();
    	map.put("user", result);
    	map.put("roleIds", roleIds);
    	return map;
    }
    
    @RequiresPermissions("sys:user:update")
    @Override
    public int updateObject(SysUser entity, 
    		Integer[] roleIds) {
    	//1.判定参数有效性
    	if(entity==null)
    	throw new IllegalArgumentException("保存对象不能为空");
    	if(StringUtils.isEmpty(entity.getUsername()))
    	throw new IllegalArgumentException("用户不能为空");
    	//2.更新用户自身信息
    	if(!StringUtils.isEmpty(entity.getPassword())){
    	String salt=UUID.randomUUID().toString();
    	SimpleHash sh=new SimpleHash(//需要先添加shiro-spring依赖
    			"MD5",//algorithmName 加密算法
    			entity.getPassword(),//source被加密的对象
    			salt, //salt盐值(使用产生的随机字符串)
    			1);//hashIterations 加密次数
    	entity.setSalt(salt);
    	entity.setPassword(sh.toHex());//已加密的密码
    	}
    	int rows=sysUserDao.updateObject(entity);
    	//3.删除用户对应的角色信息
    	sysUserRoleDao.deleteObjectsByUserId(entity.getId());
    	//4.保存用户与角色的关系数据
    	sysUserRoleDao.insertObject(entity.getId(),
    			roleIds);
    	//5.返回结果
    	return rows;
    }
    @Override
    public int saveObject(SysUser entity, 
    		Integer[] roleIds) {
    	//1.判定参数有效性
    	if(entity==null)
    	throw new IllegalArgumentException("保存对象不能为空");
    	if(StringUtils.isEmpty(entity.getUsername()))
    	throw new IllegalArgumentException("用户不能为空");
    	if(StringUtils.isEmpty(entity.getPassword()))
    	throw new IllegalArgumentException("密码不能为空");
    	//2.保存用户自身信息
    	String salt=UUID.randomUUID().toString();
    	SimpleHash sh=new SimpleHash(//需要先添加shiro-spring依赖
    		   "MD5",//algorithmName 加密算法
    			entity.getPassword(),//source被加密的对象
    			salt, //salt盐值(使用产生的随机字符串)
    			1);//hashIterations 加密次数
    	entity.setSalt(salt);
    	entity.setPassword(sh.toHex());
    	int rows=sysUserDao.insertObject(entity);
    	//3.保存用户与角色的关系数据
    	sysUserRoleDao.insertObject(entity.getId(),
    			roleIds);
    	//4.返回结果
    	return rows;
    }
    
    @Override
    public int validById(Integer id, 
    		Integer valid, String modifiedUser) {
    	//1.参数有效性验证
    	if(id==null||id<1)
    	throw new IllegalArgumentException("参数id值无效");
    	if(valid==null||(valid!=0&&valid!=1))
    	throw new IllegalArgumentException("状态值不正确");
    	//2.禁用或启用用户
    	int rows=
    	sysUserDao.validById(id, valid, modifiedUser);
    	//3.验证结果
    	if(rows==0)
    	throw new ServiceException("记录可能已经不存在");
    	//4.返回结果
    	return rows;
    }
    
	@Override
	public PageObject<SysUserDeptResult> findPageObjects(
			String username, Integer pageCurrent) {
	    //1.参数有效性验证
		if(pageCurrent==null||pageCurrent<1)
		throw new IllegalArgumentException("当前页码值无效");
		//2.查询总记录数,并进行验证
		int rowCount=sysUserDao.getRowCount(username);
		if(rowCount==0)
		throw new ServiceException("没找到对应记录");
		//3.查询当前页数据
		int pageSize=3;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysUserDeptResult> records=
		sysUserDao.findPageObjects(username,
				startIndex, pageSize);
		//4.封装查询结果
		PageObject<SysUserDeptResult> po=new PageObject<>();
		po.setRecords(records);
		po.setRowCount(rowCount);
		po.setPageSize(pageSize);
		po.setPageCurrent(pageCurrent);
		po.setPageCount((rowCount-1)/pageSize+1);
		//5.返回查询结果
		return po;
	}

}










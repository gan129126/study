package com.jt.sys.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jt.common.annotation.RequestLog;
import com.jt.common.exception.ServiceException;
import com.jt.common.vo.PageObject;
import com.jt.sys.dao.SysConfigDao;
import com.jt.sys.entity.SysConfig;
import com.jt.sys.service.SysConfigService;
//AOP: Proxy.newProxyInstance(.....)
@Service
@Transactional(rollbackFor=Throwable.class,propagation=Propagation.REQUIRED)
public class SysConfigServiceImpl implements SysConfigService{
	@Autowired
	private SysConfigDao sysConfigDao;
	@Override
	public int updateObject(SysConfig entity) {
		//1.参数有效性验证
		if(entity==null)
		throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
		throw new IllegalArgumentException("参数名不能为空");
		if(StringUtils.isEmpty(entity.getValue()))
		throw new IllegalArgumentException("参数值不能为空");
		System.out.println(entity);
		//2.保存对象
		int rows=sysConfigDao.updateObject(entity);
		//3.验证结果
		if(rows==0)
		throw new ServiceException("记录可能已经不存在了");
		//4.返回数据
		return rows;
	}
	@Override
	public int saveObject(SysConfig entity) {
		//1.参数有效性验证
		if(entity==null)
		throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
		throw new IllegalArgumentException("参数名不能为空");
		if(StringUtils.isEmpty(entity.getValue()))
		throw new IllegalArgumentException("参数值不能为空");
		//2.保存对象
		int rows=sysConfigDao.insertObject(entity);
		//3.验证结果
		if(rows==1)
		throw new ServiceException("save error");
		//4.返回数据
		return rows;
	}
	
	@Override
	public int deleteObjects(Integer... ids) {
        //System.out.println(System.nanoTime());
		//1.参数合法性验证.
		if(ids==null||ids.length==0)
		throw new IllegalArgumentException("至少选择一条记录");
		//2.执行删除操作
		int rows=-1;
		try{
		rows=sysConfigDao.deleteObjects(ids);
		}catch(Throwable e){//Error,Exception
		e.printStackTrace();
		//给运维发短信,或者切换到另一台服务器
		throw new ServiceException("系统运维中稍等片刻");
		}
		//3.验证删除结果
		if(rows==0)
		throw new ServiceException("记录可能已经不存在");
		//4.返回结果
		return rows;
	}
	/**
	 * 通过@RequestLog自定义注解对方法业务进行描述
	 */
	@RequestLog("配置信息分页查询")
	@Transactional(readOnly=true,isolation=Isolation.READ_UNCOMMITTED,timeout=30)
	@Override
	public PageObject<SysConfig> findPageObjects(String name, Integer pageCurrent) {
		//1.判定参数的有效性
		if(pageCurrent==null||pageCurrent<1)
		throw new IllegalArgumentException("页码值不正确");
		//2.查询总记录数
		int rowCount=sysConfigDao.getRowCount(name);
		//3.验证总记录数
		if(rowCount==0)
	    throw new ServiceException("没有找到对应记录");
		//4.分页查询当前页记录
		Integer pageSize=2;
		Integer startIndex=(pageCurrent-1)*pageSize;
		List<SysConfig> records=
		sysConfigDao.findPageObjects(name,
				startIndex, pageSize);
		//5.封装查询结果
		PageObject<SysConfig> po=new PageObject<>();
		po.setRecords(records);
		po.setRowCount(rowCount);
		po.setPageCount((rowCount-1)/pageSize+1);
		po.setPageCurrent(pageCurrent);
		po.setPageSize(pageSize);
		return po;
	}
}








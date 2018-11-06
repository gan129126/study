package com.jt.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jt.common.vo.Node;
import com.jt.sys.entity.SysMenu;

public interface SysMenuDao {
	/**
	 * 更新菜单信息
	 * @param entity
	 * @return
	 */
	int updateObject(SysMenu entity);
	
	/**
	 * 将数据持久化到数据库
	 * @param entity
	 * @return 写入到数据库表中数据的行数
	 * 假如参数使用了@Param("entity")
	 * 注解修饰,那么在mapper中获取对象值
	 * 时需要加上@Param注解中定义的参数
	 * 作为前缀.例如#{entity.name}
	 */
	int insertObject(SysMenu entity);

	/**
	 * 查询菜单节点信息,将此信息最后
	 * 呈现在zTree对象上.
	 * @return
	 */
	List<Node> findZtreeMenuNodes();
	
	/**
	  * 根据菜单id统计子菜单的个数
	  * @param id
	  * @return
	  */
	 int getChildCount(Integer id);
	 
	 /**
	  * 根据id 删除菜单
	  * @param id
	  * @return
	  */
	 int deleteObject(Integer id);

	
	/**
	 * 查询所有菜单以及上一级菜单
	 * FAQ?
	 * 1)假如本菜单没有上一级菜单是否要呈现?要
	 * 2)当菜单为一级菜单时它的上级菜单默认为null?
	 * 这样的菜单,采用怎样的sql实现查询操作?
	 * 方案:表关联或嵌套查询
	 */
	 List<Map<String,Object>> findObjects();
}







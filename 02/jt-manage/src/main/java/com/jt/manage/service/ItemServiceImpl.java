package com.jt.manage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.vo.EasyUIResult;
import com.jt.manage.mapper.ItemDescMapper;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private ItemDescMapper itemDescMapper;

	@Override
	public EasyUIResult findItemByPage(int page, int rows) {

		//查询商品的记录总数
		//int total = itemMapper.findCount();
		//null表示查询语法中没有where条件
		int total = itemMapper.selectCount(null);
		/**
		 * limit 起始位置,查询条数
		 * 第一页  select * from tb_user limit 0,20
		 * 第二页  select * from tb_user limit 20,20
		 * 第三页  select * from tb_user limit 40,20
		 * 第N页   select * from tb_user limit (page-1)*rows,rows
		 */
		int start = (page-1) * rows;
		List<Item> itemList = itemMapper.findItemByPage(start,rows);

		return new EasyUIResult(total,itemList);
	}

	@Override
	public String findItemCatNameById(Long itemId) {

		return itemMapper.findItemCatNameById(itemId);
	}

	//当用户添加商品时，需要同时入库2张表数据
	@Override
	public void saveItem(Item item,String desc) {
		//封装数据
		item.setStatus(1);   					//1表示正常
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		itemMapper.insert(item);				//利用mybatis实现入库
		
		//没有item的主键如何获取数据????
		//Item itemDB = itemMapper.select(item);????
		
		//入库itemDesc表
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setCreated(item.getCreated());
		itemDesc.setUpdated(item.getCreated());
		itemDesc.setItemDesc(desc);
		itemDescMapper.insert(itemDesc);
	}

	@Override
	public void updateItem(Item item,String desc) {
		item.setUpdated(new Date());
		//动态更新操作 将对象中不为null的数据充当set条件 主键除外
		itemMapper.updateByPrimaryKeySelective(item);
		
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(item.getCreated());
		itemDescMapper.updateByPrimaryKeySelective(itemDesc);
	}

	@Override
	public void deleteItems(Long[] ids) {
		//关联删除时，先删除从表数据，之后删除主表数据
		//因为定义了主外键关联关系
		itemMapper.deleteByIDS(ids);
		itemDescMapper.deleteByIDS(ids);
	}

	@Override
	public void updateStatus(Long[] ids, int status) {
		itemMapper.updateStatus(ids,status);
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) {
		return itemDescMapper.selectByPrimaryKey(itemId);
	}

	@Override
	public Item findItemById(Long itemId) {
		
		return itemMapper.selectByPrimaryKey(itemId);
	}
}

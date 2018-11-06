package com.jt.order.service;

import java.util.Date;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.order.mapper.OrderItemMapper;
import com.jt.order.mapper.OrderMapper;
import com.jt.order.mapper.OrderShippingMapper;
import com.jt.order.pojo.Order;
import com.jt.order.pojo.OrderItem;
import com.jt.order.pojo.OrderShipping;

//@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@Autowired
	private OrderShippingMapper orderShippingMapper;

	//操作rabbitmq服务器对象
	@Autowired
	RabbitTemplate rabbitTemplate;	
	@Override
	public String saveOrder(Order order) {
		//拼接orderId号
		String orderId = order.getUserId() + "" + System.currentTimeMillis();
		
		
		order.setOrderId(orderId);

		//原先直接入库，数据很多，入库很慢
		//把订单发到消息队列
		String routingKey="saveOrder";
		//把order对象序列化，变成字符串，发到rabbitmq服务器上
		rabbitTemplate.convertAndSend(routingKey, order);
		//入库订单

//		orderMapper.insert(order);
//		System.out.println("订单入库成功!!!!!");
//		
//		//获取订单物流信息
//		OrderShipping orderShipping = order.getOrderShipping();
//		orderShipping.setOrderId(orderId);
//		orderShipping.setCreated(date);
//		orderShipping.setUpdated(date);
//		orderShippingMapper.insert(orderShipping);
//		System.out.println("订单物流信息入库成功!!");
//		
//		//实现订单商品入库
//		List<OrderItem> orderItemList = order.getOrderItems();
//		for (OrderItem orderItem : orderItemList) {
//			orderItem.setOrderId(orderId);
//			orderItem.setCreated(date);
//			orderItem.setUpdated(date);
//			orderItemMapper.insert(orderItem);
//		}
//		System.out.println("订单入库成功!!!");
		return orderId;
	}
	
	//三张表同时查询
	/**
	 *思路:
	 *	1.where 2.left join  tb_order tb_order_shipping tb_order_item
	 *	Mybatis规定:结果集中禁止出现重名字段. mybaits进行结果集映射时必定报错.
	 *	select a.order_id as order_id,b.order_id as b_order_id from  tb_order a,tb_order_shipping b,tb_order_item c
	 *	where a.order_id = #{orderId} and a.order_id = b.order_id and b.order_id = c.order_id
	 *	
	 *	2.左连接查询
	 *		select *  from 
(select o.order_id............... from
tb_order o
	LEFT JOIN
tb_order_item b 
	on 
	o.order_id = b.order_id
where o.order_id = '71521427672071')c
	LEFT JOIN
tb_order_shipping d
	on c.order_id = d.order_id
	 *
	 *  resultMap实现数据库映射.
	 */
	@Override
	public Order findOrderById(String orderId) {
		//1.获取order数据  2.获取orderShipping对象 3.获取orderItem数据
		//4.将数据进行组装
		Order order = orderMapper.selectByPrimaryKey(orderId);
		OrderShipping orderShipping = orderShippingMapper.selectByPrimaryKey(orderId);
		
		OrderItem orderItem = new OrderItem();
		orderItem.setOrderId(orderId);
		List<OrderItem> orderItems = orderItemMapper.select(orderItem);
		
		//数据封装
		order.setOrderShipping(orderShipping);
		order.setOrderItems(orderItems);
		return order;
	}
}

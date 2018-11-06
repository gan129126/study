package com.tedu.rabbitmq;

import java.io.IOException;

import org.junit.Test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Test_5_topic_provider {
	@Test
	public void provider() throws Throwable
	{
		//1,建立连接
		ConnectionFactory factory
		=new ConnectionFactory();
		factory.setHost("192.168.32.201");
		factory.setPort(5672);
		factory.setUsername("jtadmin");
		factory.setPassword("jtadmin");
		factory.setVirtualHost("/jt");
		//2,创建通道
		//com.rabbitmq.client.Connection
		Connection connection=factory.newConnection();
		//com.rabbitmq.client
		Channel channel=connection.createChannel();
		
		String exchangeName="e3";
		//创建交换机
		//p2:type 
		//fanout订阅模式 
		//direct 路由模式
		//topic 主题模式
		channel.exchangeDeclare
		(exchangeName, "topic");
		channel.basicPublish
		(exchangeName, "agent.cart", null, "msg1".getBytes());
		channel.basicPublish
		(exchangeName, "agent.order", null, "msg2".getBytes());
		channel.basicPublish
		(exchangeName, "agent.test", null, "msg3".getBytes());
		channel.basicPublish
		(exchangeName, "domestic.cart", null, "msg4".getBytes());
		channel.close();
		connection.close();
		//订阅，路由，主题，数据发成功后，后台看不到数量
	}

}

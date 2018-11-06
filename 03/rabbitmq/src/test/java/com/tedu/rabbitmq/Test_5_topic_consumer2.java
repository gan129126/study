package com.tedu.rabbitmq;

import java.io.IOException;
import java.util.UUID;

import org.junit.Test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

public class Test_5_topic_consumer2 {
	@Test
	public void consumer() throws Throwable
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
		//指定交换机
		String exchangeName="e3";
		channel.exchangeDeclare
		(exchangeName, "topic");		
		//3,创建队列
		String queueName=UUID.randomUUID().toString();
		System.out.println("主题 消费者2 队列名称"+queueName);
		channel.queueDeclare
		(queueName, true, false, false, null);
		//拷贝consumer1改consumer2,
		//routingkey改成domestic
		//把队列和交换机绑定
		channel.queueBind
		(queueName, exchangeName, "domestic.*");
		//每次取几个数据
		channel.basicQos(1);
		//4,创建消费者
		QueueingConsumer consumer=
				new QueueingConsumer(channel);
		//5,消费者和队列绑定
		channel.basicConsume
		(queueName, true, consumer);
		//6，多个数据list,用迭代器
		while(true)
		{
			//取数据
			Delivery delivery=consumer.nextDelivery();
			byte[] data=delivery.getBody();
			String string=new String(data);
			System.out.println("消费者2取到："+string);
		}
		
	}

}

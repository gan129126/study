package com.jt.jt_redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class TestJedis {
	@Test	//完成单实例链接
	public void jedis(){
		Jedis jedis = new Jedis("192.168.32.202", 7000);
		//jedis.auth("123456");
		jedis.set("name", "tony");	//调用redis命令set
		String s = jedis.get("name");
		System.out.println(s);
		jedis.close();
	}
}

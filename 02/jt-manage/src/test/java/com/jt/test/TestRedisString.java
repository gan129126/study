package com.jt.test;

import java.util.Map;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class TestRedisString {

	@Test
	public void test01() {
		Jedis jedis = new Jedis("192.168.32.138",6379);
		jedis.set("1805", "好好学习，早日迎娶白富美");
		System.out.println(jedis.get("1805"));
		
	}
	
	@Test
	public void test02() {
		Jedis jedis = new Jedis("192.168.32.138",6379);
		jedis.hset("user", "id", "100");
		jedis.hset("user", "name", "1805班");
		
		Map<String, String> userMap = jedis.hgetAll("user");
		System.out.println(userMap);
	}
	
	@Test
	public void  test03() {
		Jedis jedis = new Jedis("192.168.32.138",6379);
		
		jedis.lpush("1805List", "1", "2", "3", "4", "5", "6");
//		System.out.println(jedis.rpop("1805List"));
		System.out.println(jedis.lpop("1805List"));
		System.out.println(jedis.lpop("1805List"));
		System.out.println(jedis.lpop("1805List"));
		System.out.println(jedis.lpop("1805List"));
		System.out.println(jedis.lpop("1805List"));
		System.out.println(jedis.lpop("1805List"));
	}
	
}

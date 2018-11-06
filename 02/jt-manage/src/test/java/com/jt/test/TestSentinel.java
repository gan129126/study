package com.jt.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class TestSentinel {

	@Test
	public void test01() {
		Set<String> sentinels = new HashSet<>();
		sentinels.add("192.168.32.138:26379");
		JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster", sentinels);
		
		Jedis jedis = sentinelPool.getResource();
		
		jedis.set("name", "tomcat");
		System.out.println(jedis.get("name"));
	}
}

package com.jt.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class TestShard {

	@Test
	public void test01() {
		//1.定义连接池大小
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(1000);
		poolConfig.setMaxIdle(100);
		poolConfig.setTestOnBorrow(true);
		
		//2.定义分片的List集合
		List<JedisShardInfo> shards = new ArrayList<>();
		shards.add(new JedisShardInfo("192.168.32.138",6379));
		shards.add(new JedisShardInfo("192.168.32.138",6380));
		shards.add(new JedisShardInfo("192.168.32.138",6381));
		
		//创建分片对象
		ShardedJedisPool jedisPool = new ShardedJedisPool(poolConfig, shards);
		ShardedJedis shardedJedis = jedisPool.getResource();
		shardedJedis.set("fenpian", "分片");
		System.out.println("获取数据："+shardedJedis.get("fenpian"));
		shardedJedis.close();
		
		
	}
}

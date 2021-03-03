package com.hanhan.application.controller;

import java.util.concurrent.TimeUnit;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class BaseRedisService {

	private static RedisTemplate redisTemplate;


	public static   void setString(String key, Object data, Long timeout) {
		System.out.println(redisTemplate);
		if (data instanceof String) {
			String value = (String) data;
			redisTemplate.opsForValue().set(key, value);
		}
		if (timeout != null) {
			redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
		}
	}

	public static Object getString(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	public  void delKey(String key) {
		redisTemplate.delete(key);
	}

	@Autowired
	public void setRedisTemplate(RedisTemplate redisTemplate){
		BaseRedisService.redisTemplate = redisTemplate;
	}
}
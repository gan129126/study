package com.jt.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.manage.pojo.User;

public class TestobjectMapper {

	@Test
	public void javaToJSON() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		User user = new User();
		user.setId(1);
		user.setName("tomcat");
		user.setAge(10);
		
		String userJSON = objectMapper.writeValueAsString(user);
		System.out.println(userJSON);
		
		//将json串转化为Java对象
		User user1 = objectMapper.readValue(userJSON, User.class);
		System.out.println(user1.toString());
	}
	
	@Test
	public void listToJSON() {
		List<String> stringList = new ArrayList<>();
	}
	
}

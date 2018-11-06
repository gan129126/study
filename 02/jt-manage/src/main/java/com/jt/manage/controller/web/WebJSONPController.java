package com.jt.manage.controller.web;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.manage.pojo.User;

@Controller
@RequestMapping("/web")
public class WebJSONPController {

	//@RequestMapping(value="/testJSONP",produces="text/html;charset=utf-8")
	//@ResponseBody
	public String jsonp(String callback) throws JsonProcessingException {
		User user = new User();
		user.setId(100);
		user.setName("tomcat猫");
		ObjectMapper objectMapper = new ObjectMapper();
		String userJSON = objectMapper.writeValueAsString(user);
		return callback+"("+userJSON+")";
	}
	
	@RequestMapping(value="/testJSONP")
	@ResponseBody
	public MappingJacksonValue jsonpSuper(String callback) {
		User user = new User();
		user.setId(100);
		user.setName("toncat猫");
		MappingJacksonValue value = new MappingJacksonValue(user);
		value.setJsonpFunction(callback);
		return value;
	}
	
}

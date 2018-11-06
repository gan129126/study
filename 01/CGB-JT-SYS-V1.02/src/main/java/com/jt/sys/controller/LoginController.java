package com.jt.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;

@Controller
@RequestMapping("/")
public class LoginController {
	 
	  @RequestMapping("doLogin")
	  @ResponseBody
	  public JsonResult doLogin(
			  String username,
			  String password){
		  System.out.println(username+"/"+password);
		  //对用户身份进行认证
		  //1.获取主体对象(对此对象进行认证)
		  Subject subject=
		  SecurityUtils.getSubject();
		  //2.提交用户信息
		  UsernamePasswordToken token=
		  new UsernamePasswordToken(
				  username,
				  password);
		  subject.login(token);
		  //思考:
		  //1.这个token提交谁了?SecurityManager
		  //2.提交这个token的目的是什么?
		  //由SecurityManager调用认证管理器完成认证操作
		  return new JsonResult("login ok");
	  }
}





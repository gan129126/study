package com.jt.common.controller;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;

@ControllerAdvice
public class GlobalExceptionHandler {
	  public GlobalExceptionHandler() {
		System.out.println("GlobalExceptionHandler()");
	  }
	  
	  
	  @ExceptionHandler(ShiroException.class)
	  @ResponseBody
	  public JsonResult doHandleShiroException(
			  ShiroException e){
		  e.printStackTrace();
		  JsonResult result=new JsonResult();
		  result.setState(0);//error
		  if(e instanceof IncorrectCredentialsException){
			result.setMessage("密码不正确");//message
			return result;
		  }if(e instanceof AuthorizationException){
			result.setMessage("没权限执行此操作");//message
			return result;
		  }
		  result.setMessage(e.getMessage());
		  return result;
	  }
	  
	  @ExceptionHandler(RuntimeException.class)
	  @ResponseBody
	  public JsonResult doHandleRuntimeException(
			  RuntimeException e){
		  e.printStackTrace();
		  return new JsonResult(e);
	  }
}

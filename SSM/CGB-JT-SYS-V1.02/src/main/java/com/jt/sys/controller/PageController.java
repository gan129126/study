package com.jt.sys.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {
	  /**通过此方法直接返回首页页面*/
      @RequestMapping("doIndexUI")
	  public String doIndexUI(){
		  return "starter";//.html
	  }
      /**此方法用于返回分页页面*/
      @RequestMapping("doPageUI")
      public String doPageUI(){
    	  return "common/page";//WEB-INF/pages/common/page.html
      }
      /**返回登录页面*/
      @RequestMapping("doLoginUI")
      public String doLoginUI(){
    	  return "login";
      }   
}




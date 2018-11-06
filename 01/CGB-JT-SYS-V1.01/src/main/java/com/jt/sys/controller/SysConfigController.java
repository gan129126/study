package com.jt.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysConfig;
import com.jt.sys.service.SysConfigService;

@Controller
@RequestMapping("/config/")
public class SysConfigController {
	  @Autowired
	  private SysConfigService sysConfigService;
	  @RequestMapping("doConfigListUI")
	  public String doConfigListUI(){
		  return "sys/config_list";
	  }
	  @RequestMapping("doConfigEditUI")
	  public String doConfigEditUI(){
		  return "sys/config_edit";
	  }

	  /**保存配置信息
	   * @RequestBody应用:
	   * 处理post请求,并且必须设置post请求的ContentType
	   * 例如:客户端向服务端传递是json格式的字符串,
	   * 服务端需要将这个串转换为java对象.
	   * */
	  @PostMapping(value="doUpdateObject",
			  produces="application/json;charset=utf-8")
	  @ResponseBody
	  public JsonResult doUpdateObject(
			  @RequestBody SysConfig entity){
		  System.out.println("controller.entity="+entity);
		  sysConfigService.updateObject(entity);
		  return new JsonResult("update ok");
	  }
	  /**保存配置信息*/
	  @PostMapping(value="doSaveObject",
			  produces="application/json;charset=utf-8")
	  @ResponseBody
	  public JsonResult doSaveObject(
			  @RequestBody SysConfig entity){
		  sysConfigService.saveObject(entity);
		  return new JsonResult("save ok");
	  }
	  /**删除配置信息*/
	  @RequestMapping(value="doDeleteObjects/{ids}",produces="application/json;charset=utf-8")
	  @ResponseBody
	  public JsonResult doDeleteObjects(
			  @PathVariable Integer... ids){//rest 风格url应用
		  sysConfigService.deleteObjects(ids);
		  return new JsonResult("delete ok");
	  }
	  
	  @GetMapping("doFindPageObjects")
	  @ResponseBody
	  public JsonResult doFindPageObjects(
			  String name,
			  Integer pageCurrent){
		  System.out.println("name="+name);
		  PageObject<SysConfig> pageObject=
		  sysConfigService.findPageObjects(
		  name,pageCurrent);
		  return new JsonResult(pageObject);
	  }//方法运行时底层会将返回的对象转换为json串
	  
}





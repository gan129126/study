package com.jt.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.sys.entity.SysMenu;
import com.jt.sys.service.SysMenuService;
@Controller
@RequestMapping("/menu/")
public class SysMenuController {
	@Autowired
	private SysMenuService sysMenuService;
	
	@RequestMapping("doMenuListUI")
	public String doMenuListUI(){
	  return "sys/menu_list";
	}
	
	/**
	 * 通过此方法返回一个编辑页面.
	 * @return
	 */
	@RequestMapping("doMenuEditUI")
	public String doMenuEditUI(){
		return "sys/menu_edit";
	}
	
	@PostMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(
			SysMenu entity){
		sysMenuService.updateObject(entity);
		return new JsonResult("update ok");
	}
	
	@PostMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(
		   SysMenu entity){
		sysMenuService.saveObject(entity);
		return new JsonResult("save ok");
	}
	
	@RequestMapping("doFindZtreeMenuNodes")
	@ResponseBody
	public JsonResult doFindZtreeMenuNodes(){
		return new JsonResult(
		 sysMenuService.findZtreeMenuNodes());
	}

	@ResponseBody
	@RequestMapping("doFindObjects")
	public JsonResult doFindObjects(){
	  return new JsonResult(
			 sysMenuService.findObjects());
	}
	/**rest 风格的url
	 * @PathVariable 修饰的变量用户获取url中的参数值.
	 * @param id
	 * @return
	 */
	@RequestMapping("doDeleteObject/{id}")
	@ResponseBody
	public JsonResult doDeleteObject(
			@PathVariable Integer id){
		sysMenuService.deleteObject(id);
		return new JsonResult("delete ok");
	}
	
}


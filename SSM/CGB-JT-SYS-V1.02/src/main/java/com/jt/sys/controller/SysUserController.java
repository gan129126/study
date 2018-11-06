package com.jt.sys.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysUser;
import com.jt.sys.service.SysUserService;
import com.jt.sys.vo.SysUserDeptResult;
import com.mysql.jdbc.Security;

@Controller
@RequestMapping("/user/")
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;

	@RequestMapping("doUserListUI")
	public String doUserListUI() {
		return "sys/user_list";
	}
	@RequestMapping("doUserEditUI")
	public String doUserEditUI(){
		return "sys/user_edit";
	}
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(
			Integer id){
		return new JsonResult(
	   sysUserService.findObjectById(id));
	}
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(
			SysUser entity,
			Integer[] roleIds){
		sysUserService.updateObject(entity,
				roleIds);
		return new JsonResult("update ok");
	}
	@RequestMapping("doSaveObject")
	@ResponseBody
    public JsonResult doSaveObject(
    		SysUser entity,
    		Integer[] roleIds){
		SysUser user=(SysUser)
		SecurityUtils.getSubject().getPrincipal();
    	entity.setModifiedUser(user.getUsername());
    	entity.setCreatedUser(user.getUsername());
		sysUserService.saveObject(entity,roleIds);
    	return new JsonResult("save ok");
    }
	/**
	 * @param id
	 * @param valid
	 * @return
	 */
	@RequestMapping("doValidById")
	@ResponseBody
	public JsonResult doValidById(
			Integer id,
			Integer valid){
		//用户登录成功以后会自动将用户信息存入session对象
		SysUser user=(SysUser)
		SecurityUtils.getSubject().getPrincipal();
		sysUserService.validById(id, valid,
				user.getUsername());
		return new JsonResult("update ok");
	}

	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(
			String username,
			Integer pageCurrent) {
		PageObject<SysUserDeptResult> pageObject = sysUserService.findPageObjects(username, pageCurrent);
		List<SysUserDeptResult> list=
		pageObject.getRecords();
		for(SysUserDeptResult s:list){
			System.out.println(s);
		}
		return new JsonResult(pageObject);
	}

}

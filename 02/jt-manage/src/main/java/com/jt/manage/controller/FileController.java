package com.jt.manage.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.vo.PicUploadResult;
import com.jt.manage.service.FileService;

@Controller
public class FileController {
	
	@Autowired
	private FileService fileService;

	/**
	 * 注意事项：
	 * 	如果参数需要赋值，需要通过springMVC提供的解析器才可以
	 * 	参数接收，必须和页面提交的name属性相同，否则参数不能提交，程序报404错误
	 * @param image
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/file")
	public String imageFile(MultipartFile image) throws Exception {
		
		//1.定义文件上传的目录
		File imageFile = new File("E:/jt-upload");
		
		//2.判断文件是否存在
		if(!imageFile.exists()) {
			//创建文件夹
			imageFile.mkdirs();
		}
		
		//3.获取文件名称
		String fileName = image.getOriginalFilename();
		
		//4.实现文件上传  文件路径/文件名称
		image.transferTo(new File("E:/jt-upload/"+fileName));
		System.out.println("文件上传成功");
		
		//5.跳转到index页面
		return "index";
	}
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public PicUploadResult fileUpload(MultipartFile uploadFile) {
		
		return fileService.fileUpload(uploadFile);
	}
}

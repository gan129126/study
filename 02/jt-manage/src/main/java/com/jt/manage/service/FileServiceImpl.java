package com.jt.manage.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.vo.PicUploadResult;

@Service
public class FileServiceImpl implements FileService{
	@Value("${image.localPath}")	//可以动态获取spring容器中key的值
	private String localPath;		// = "E:/jt-upload/";
	@Value("${image.urlPath}")
	private String urlPath;			// = "http://image.jt.com/";
	
	/**
	 * 1.判断图片的类型jpg|png|gif
	 * 2.判断是否为恶意程序
	 * 3.为了提高检索的效率，将文件分文件存储
	 * 		UUID:hash随机算法(当前的毫秒数+算法+hash)
	 * 4.如何杜绝文件重名现象
	 * 	uuid+随机数(100).jpg
	 * 5.实现文件上传
	 */
	@Override
	public PicUploadResult fileUpload(MultipartFile uploadFile) {
		PicUploadResult uploadResult = new PicUploadResult();
		
		//1.判断是否为图片类型
		String fileName = uploadFile.getOriginalFilename();
		fileName = fileName.toLowerCase();	//将字符全部转化为小写
		
		if(!fileName.matches("^.*\\.(jpg|png|gif)$")) {
			uploadResult.setError(1);
			return uploadResult;	//文件不是图片
		}
		
		//2.判断是否为恶意程序
		try {
			BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
			
			int height = bufferedImage.getHeight();	//获取图片的高度
			int width = bufferedImage.getWidth();	//获取图片的宽度
			
			if(height == 0 || width == 0) {
				//表示不是图片
				uploadResult.setError(1);
				return uploadResult;
			}//程序执行到这里 证明是一张图片
			
			//为文件进行分文件存储	yyyy/MM/dd
			String dataPath = 
					new SimpleDateFormat("yyyy/MM/dd").format(new Date());
			
			//定义文件保存路径
			String dirPath= localPath + dataPath;
			
			//判断文件夹是否存在
			File dirFile = new File(dirPath);
			if(!dirFile.exists()) {
				//文件不存在时，应该创建文件夹
				dirFile.mkdirs();
			}
			
			//4.动态生成文件名称UUID+三位随机数
			String uuid = UUID.randomUUID().toString().replace("-", "");
			int randomNum = new Random().nextInt(1000);
			String imageFileType = fileName.substring(fileName.lastIndexOf("."));
			//
			String imageFileName = uuid+randomNum+imageFileType;
			
			//5.实现文件上传
			String imageLocalPath = dirPath + "/" + imageFileName;
			uploadFile.transferTo(new File(imageLocalPath));
			
			//6.封装参数
			uploadResult.setHeight(height+"");
			uploadResult.setWidth(width+"");
			System.out.println("文件上传成功");
			
			//7.准备虚拟路径
			String imageUrlPath = urlPath + dataPath + "/" + imageFileName;
			uploadResult.setUrl(imageUrlPath);
			
		} catch (IOException e) {
			e.printStackTrace();
			uploadResult.setError(1);
			return uploadResult;
		}
		return uploadResult;
	}

	
}

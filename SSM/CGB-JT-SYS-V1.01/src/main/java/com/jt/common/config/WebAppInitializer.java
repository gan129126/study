package com.jt.common.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
/**Web服务器启动时会自动加载此类(
 * 相当于web.xml)*/
public class WebAppInitializer extends 
             AbstractAnnotationConfigDispatcherServletInitializer {
    /**此方法中自动注册了前端控制器*/
	@Override
    public void onStartup(ServletContext servletContext) throws ServletException {
    	super.onStartup(servletContext);
    }
	/**此方法负责加载service,dao*/
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{AppRootConfig.class};
	}
    /**负责加载Spring MVC 组件*/
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{AppServletConfig.class};
	}
	/**配置映射路径*/
	@Override
	protected String[] getServletMappings() {
		return new String[]{"*.do"};
	}
}

package com.jt.common.config;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
/**Web服务器启动时会自动加载此类(
 * 相当于web.xml)*/
public class WebAppInitializer extends 
             AbstractAnnotationConfigDispatcherServletInitializer {
    /**此方法中自动注册了前端控制器*/
	@Override
    public void onStartup(ServletContext servletContext) throws ServletException {
    	//System.out.println("onStartup");
		//super.onStartup(servletContext);
		//注册监听器(调用父类方法)
		registerContextLoaderListener(servletContext);
    	//注册shiro的过滤器
		registerShiroFilter(servletContext);
		//注册servlet(调用父类方法)
		registerDispatcherServlet(servletContext);
    }
	/**
	 * 注册shiro中的核心过滤器
	 * @param servletContext
	 */
	private void registerShiroFilter(ServletContext servletContext){
		//System.out.println("registerShiroFilter");
		//注册过滤器
		Dynamic dFilter = servletContext.addFilter(
				"filterProxy",//filterName
				 DelegatingFilterProxy.class);
		dFilter.setInitParameter(
				"targetBeanName",//DelegatingFilterProxy对象中的参数名
				"shiroFilterFactoryBean");//这个值必须与spring管理的shiro中的bean的名字相同
		//映射过滤器
		dFilter.addMappingForUrlPatterns(
				null,//dispatcherTypes (null 表示默认所有)
		        false,//isMatchAfter
		        "/*");//urlPatterns
	}
	/**此方法负责加载service,dao*/
	@Override
	protected Class<?>[] getRootConfigClasses() {
		//System.out.println("getRootConfigClasses");
		return new Class[]{AppRootConfig.class,
				AppShiroConfig.class};
	}
    /**负责加载Spring MVC 组件*/
	@Override
	protected Class<?>[] getServletConfigClasses() {
		//System.out.println("getServletConfigClasses");
		return new Class[]{AppServletConfig.class};
	}
	/**配置映射路径*/
	@Override
	protected String[] getServletMappings() {
		//System.out.println("getServletMappings");
		return new String[]{"*.do"};
	}
}

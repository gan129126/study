package com.jt.test;

import java.util.Calendar;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestFactory {

	@Test
	public void testStaticFactory() {
		ApplicationContext app = new ClassPathXmlApplicationContext("spring/factory.xml");
		Calendar calendar = (Calendar) app.getBean("calendar1");
		System.out.println("获取时间1："+calendar.getTime());
	}
	
	@Test
	public void testInstanceFactory() {
		ApplicationContext app = new ClassPathXmlApplicationContext("spring/factory.xml");
		Calendar calendar = (Calendar) app.getBean("calendar2");
		System.out.println("获取时间2："+calendar.getTime());
	}
	
	@Test
	public void testSpringFactory() {
		ApplicationContext app = new ClassPathXmlApplicationContext("spring/factory.xml");
		Calendar calendar = (Calendar) app.getBean("calendar3");
		System.out.println("获取时间3："+calendar.getTime());
	}
}

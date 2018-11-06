package com.jt.common.aspect;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Order(2) //多个切面时定义切面执行顺序
@Service
@Aspect
public class SysMonitorAspect {
    @Pointcut("bean(*ServiceImpl)")
	public void monitor(){}
    /**前置通知*/
	@Before("monitor()")
	public void beforeMethod(){
	  System.out.println("@Before");
	}
	/**返回通知*/
	@AfterReturning("monitor()")
	public void returnMethod(){
		System.out.println("@AfterReturning");
	}
	/**异常通知*/
	@AfterThrowing("monitor()")
	public void throwMethod(){
		System.out.println("@AfterThrowing");
		//系统出现异常以后可以在此位置进行报警
	}
	/**后置通知*/
	@After("monitor()")
	public void afterMethod(){
		System.out.println("@After");
	}
}
/**
 * 假如将如上通知放入一种结构的化,可参考如下结构
 * try{
 *  @Before
 *  核心业务
 *  @AfterReturning
 * }catch(Exception e){
 *  @AfterThrowing
 * }finally{
 *  @After
 * }
 */








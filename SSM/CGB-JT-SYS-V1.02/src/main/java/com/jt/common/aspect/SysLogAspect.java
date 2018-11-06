package com.jt.common.aspect;

import java.lang.reflect.Method;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.jt.common.annotation.RequestLog;
import com.jt.common.util.IPUtils;
import com.jt.sys.dao.SysLogDao;
import com.jt.sys.entity.SysLog;
import com.jt.sys.entity.SysUser;
/**
 * @Aspect 注解描述的类为一个切面对象
 * 此对象通常要定义:
 * 1)通知(扩展业务)
 * 2)切入点(织入扩展业务的那些点)
 */
@Order(1)
@Service
@Aspect
public class SysLogAspect {
	@Autowired
	private SysLogDao sysLogDao;
	/**
	 * @Pointcut 注解用于定义切入点表达式:
	 * 切入点:切入扩展功能的多个连接点(具体方法)的集合
	 */
	 @Pointcut("bean(*ServiceImpl)")
	 public void sysPointCut(){}
      /**
       * @Around 注解修饰的方法为一个环绕通知对象
       * @param point 连接点(通常指核心业务方法)
       * @return
       */
	 //@Around("bean(sysConfigServiceImpl)")
	 @Around("sysPointCut()")
	 public Object around(ProceedingJoinPoint joinPoint)
	 throws Throwable{
		 long startTime=System.currentTimeMillis();  
		 //执行目标业务方法(result就是目标方法的执行结果)
		 Object result=joinPoint.proceed();
		 long endTime=System.currentTimeMillis();
		 //获取用户的操作日志,并将日志信息写入到数据库
		 saveSysLog(joinPoint, startTime, endTime);
		 return result;
	 }
	 private void saveSysLog(ProceedingJoinPoint joinPoint, long startTime, long endTime) throws NoSuchMethodException {
		 
		 //1.获取执行时长
		 long totalTime=endTime-startTime;
		 //2.获取当前操作用户
		 SysUser user=(SysUser)
		 SecurityUtils.getSubject()
		 .getPrincipal();
		 String username=user.getUsername();
		 //3.获取操作的方法(哪个类的哪个方法)
		 //3.1获取方法签名
		 MethodSignature ms=(MethodSignature)
		 joinPoint.getSignature();
		 Class<?> targetCls=joinPoint.getTarget().getClass();
		 Class<?>[] parameterTypes=ms.getParameterTypes();
	     String methodName=ms.getName();
	     String clsMethod=//存入数据库
	     targetCls.getName()+"."+methodName;
		 //4.获取操作方法时传入的实际参数
	     String args=JSON.toJSONString(joinPoint.getArgs());
	     //System.out.println("args="+args);
		 //5.获取当前操作的说明(这是什么操作)
	     //5.1获取目标方法对象
	     Method method=targetCls.getDeclaredMethod(methodName,
	    		 parameterTypes);
	    // System.out.println(method);
	     //5.2获取目标方法对象上的注解
	     RequestLog requestLog=
	     method.getDeclaredAnnotation(RequestLog.class);
	     String operation="";
	     if(requestLog!=null){
	    	 operation=requestLog.value();
	     }
		 //6.获取当前用户的ip地址
	     String ip=IPUtils.getIpAddr();
	     System.out.println(ip);
	     //7.封装日志信息
	     SysLog log=new SysLog();
	     log.setIp(ip);
	     log.setUsername(username);
	     log.setMethod(clsMethod);
	     log.setOperation(operation);
	     log.setParams(args);
	     log.setTime(totalTime);
	     //8.将日志对象存储到数据库
	     sysLogDao.insertObject(log);
	}
}







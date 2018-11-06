package reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

//定义注解
/**
 * @Target 注解用于描述你定义的注解
 * 能够修饰哪些元素
 */
@Target(value={ElementType.TYPE})//表示此注解只能描述类
@Retention(RetentionPolicy.RUNTIME)//让注解何时生效
@interface Component{
	String value() default "";
}//Component.class

@Component("cir")
class Circle{}


public class TestReflect04 {
	public static void main(String[] args)
	throws Exception{
		//1.获取Circle字节码对象
		Class<?> c=
		Class.forName("reflect.Circle");
		//2.判定类上有没有@Component注解
		boolean flag=
		c.isAnnotationPresent(Component.class);
		if(!flag)return;//spring 不管
		//3.有@Component注解修饰则获取注解对象
		Component cmt=
		c.getDeclaredAnnotation(Component.class);
		//4.获取注解对象上的value属性的值.
		String value=cmt.value();
		System.out.println(value);
	}
}

package reflect;

import java.lang.reflect.Constructor;
/**
 * 课堂练习1
 * @author 速度
 *
 */
class HelloService{
	private HelloService() {
		// TODO Auto-generated constructor stub
	}
}
public class TestReflect01 {
    /**
     * <bean id="hs" class="reflect.HelloService"/>
     * @param args
     */
	public static void main(String[] args)
	throws Exception{
		//1.获取类对象(Class)
		Class<?> c=
		Class.forName("reflect.HelloService");
		//2.通过类对象获取构造方法对象
		Constructor<?> con=
		c.getDeclaredConstructor();
		//3.设置构造方法可访问
		if(!con.isAccessible()){
			con.setAccessible(true);
		}
		//4.通过构造方法对象构建类的对象
		Object obj=con.newInstance();
		System.out.println(obj);
	}
}

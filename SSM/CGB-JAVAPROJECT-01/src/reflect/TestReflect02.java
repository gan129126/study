package reflect;

import java.lang.reflect.Constructor;

class WelcomeService{
	private String msg;
	private int a;
	public WelcomeService(String msg) {
		this.msg=msg;
	}
	public WelcomeService(String msg,int a){
		this.msg=msg;
		this.a=a;
	}
	@Override
	public String toString() {
		return "WelcomeService [msg=" + msg + ", a=" + a + "]";
	}
}
//剖析spring中构造注入
public class TestReflect02 {
	public static void main(String[] args)
	throws Exception{
		//1.获取类对象(反射应用起点):字节码对象
		Class<?> c1=
		Class.forName("reflect.WelcomeService");
		//Class<?> c2=WelcomeService.class;
		//Class<?> c3=new WelcomeService("a").getClass();
		//System.out.println(c1==c2); true
		//System.out.println(c2==c3); true
		//2.基于类对象获取构造方法对象
		Constructor<?> con=
		c1.getDeclaredConstructor(String.class,int.class);//Integer.class
		//3.设置构造方法是被访问
		if(!con.isAccessible()){
			con.setAccessible(true);
		}
		//4.通过构造方法对象构建类的实例
		Object obj=con.newInstance(
		"welcome to tedu",100);
	    System.out.println(obj);
	}
}






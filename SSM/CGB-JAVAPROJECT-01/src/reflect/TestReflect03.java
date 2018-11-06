package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

class Point{//Point.class
	private int x;//对象(Field)
	public void setX(int x) {//对象(Method)
		this.x = x;
	}
	public int getX() {//对象(Method)
		return x;
	}
	@Override
	public String toString() {//对象(Method)
		return "Point [x=" + x + "]";
	}
}
//剖析spring中set注入
public class TestReflect03 {
    public static void main(String[] args)
    throws Exception{
    	/*Point p=new Point();
    	  p.setX(10);*/
	    //1.获取字节码对象(Class)
    	Class<?> c=
    	Class.forName("reflect.Point");
    	//2.基于字节码对象获取类的构造方法对象
    	Constructor<?> con=
        c.getDeclaredConstructor();
    	//3.设置构造方法可访问
    	if(!con.isAccessible()){
    		con.setAccessible(true);
    	}
    	//4.构建类的实例对象
    	Object obj=con.newInstance();//Point类的对象
    	//5.获取类中的setX方法对象
    	String property="X";
    	Method m=c.getDeclaredMethod(
    	"set"+property,int.class);
    	if(!m.isAccessible()){
    		m.setAccessible(true);
    	}
    	//6.执行setX方法对象为属性赋值
    	//执行obj实例的m方法,并传递实际参数100.
    	m.invoke(obj,100);
    	//7.输出对象x属性的值.
    	System.out.println(obj);                  
    }
}

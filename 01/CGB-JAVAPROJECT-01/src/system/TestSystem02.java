package system;
class Point{
	/**此方法会在对象销毁之前调用*/
	@Override
	protected void finalize() 
			throws Throwable {
		System.out.println("finalize()");
	}
}
public class TestSystem02 {
	public static void main(String[] args) {
		Point p=new Point();
		p=null;
		System.gc();
	}
}

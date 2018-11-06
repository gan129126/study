package singleton;
/**课堂练习2*/
class Singleton01{
	private Singleton01(){}
	private static Singleton01 instance=
			new Singleton01();
	public static Singleton01 getInstance(){
		return instance;
	}
	//public static void show(){}
}
class Singleton02{
	private Singleton02(){}
	private static Singleton02 instance=null;
	public static Singleton02 getInstance(){
		if(instance==null){
		instance=new Singleton02();
		}
		return instance;
	}
}

public class TestSingleton01 {

	public static void main(String[] args) {
		Singleton01 s1=Singleton01.getInstance();
		Singleton01 s2=Singleton01.getInstance();
		System.out.println(s1==s2);
		
	}
}

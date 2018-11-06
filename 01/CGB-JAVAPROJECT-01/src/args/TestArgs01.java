package args;

class Args{
	/*public void doMethod(int a){
	}
	public void doMethod(int a,int b){
	}
	public void doMethod(int a,int b,int c){
	}*/
	public void doMethod(int... a){
		System.out.println(a.length);
	}
}

public class TestArgs01 {

	public static void main(String[] args) {
		Args a=new Args();
		a.doMethod();
		a.doMethod(1);
		a.doMethod(1,2);
		a.doMethod(1,2,3);
		
	}
	
}

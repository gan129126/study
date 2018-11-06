package exception;

public class TestException01 {

	static int doMethod01(int a,int b){
		int result=-1;
		try{
		result=a/b;
		return result;
		}catch(Exception e){
		throw new RuntimeException("除数不能为0");	
		}finally{
		System.out.println(result);	
		}
	}
	public static void main(String[] args) {
		 doMethod01(10,0);
	}
}

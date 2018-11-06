package oop;
interface HelloService{
	public void sayHello();
} 
class HelloServiceImpl implements HelloService{
	public void sayHello(){
		//System.out.println(System.nanoTime());
		System.out.println("helloworld");
	}
}
class SubHelloServiceImpl extends HelloServiceImpl{
	@Override
	public void sayHello() {
		System.out.println(System.nanoTime());
		super.sayHello();
	}
}
class HelloServiceImplProxy implements HelloService{
	private HelloService helloService;
	public HelloServiceImplProxy(HelloService helloService) {
		this.helloService=helloService;
	}
	@Override
	public void sayHello() {
		System.out.println(System.nanoTime());
		helloService.sayHello();
	}
}
public class TestOOP01 {

	public static void main(String[] args) {
		 HelloService h=
		 new HelloServiceImplProxy(
		     new HelloServiceImpl());
		 h.sayHello();
	}
}






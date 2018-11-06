package system;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class TestSystem01 {
	public static void main(String[] args) {
		 InputStream in=System.in;
		 PrintStream ps=System.out;
		 ps.println("helloworld");
	     String s=System.getProperty("user.name");
	     System.out.println(s);
	     String version=
	     System.getProperty("java.version");
	     System.out.println(version);
	     Properties pros=System.getProperties();
	     System.out.println(pros);
	     Set<Entry<Object,Object>> set=pros.entrySet();
	     for(Entry<Object,Object> en:set){
	    	 System.out.println(en);
	     }  
	}
}

package date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestCalendar01 {
	public static void main(String[] args) {
		//获取日期对象并获取对应的毫秒
		Date d1=new Date();
		long t1=d1.getTime();
		System.out.println(t1);
		//通过System对象获取当前时间(毫秒)
		long t2=System.currentTimeMillis();
		System.out.println(t2);
		//获取一个日历对象(Calendar对象还可以用于日期的加减运算)
		Calendar c1=Calendar.getInstance();
		long t3=c1.getTimeInMillis();
		System.out.println(t3);
		c1.set(Calendar.HOUR_OF_DAY, 9);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);
		//获取当前时间的毫秒数
		long t4=c1.getTimeInMillis();
		System.out.println(t4);
		Date d2=c1.getTime();
		SimpleDateFormat sdf=
	    new SimpleDateFormat(
	    "yyyy/MM/dd HH:mm:ss");
		String s1=sdf.format(d2);
		System.out.println(s1);
		
		
		
		
		
		
		
		
		
		
		
	}
}

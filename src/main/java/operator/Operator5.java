package operator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Operator5 {
	public static void main(String[] args) throws ParseException {
		Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-06-15 00:00:00");
		Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-06-17 22:00:00");
		int begin;
		int end;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		begin = cal.get(Calendar.DAY_OF_YEAR);
		cal.setTime(date2);
		end = cal.get(Calendar.DAY_OF_YEAR);
		System.err.println(end - begin);
	}
}

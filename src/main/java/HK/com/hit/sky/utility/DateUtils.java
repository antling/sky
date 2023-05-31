package HK.com.hit.sky.utility;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import HK.com.hit.sky.model.NullNumberInterpreter;
import HK.com.hit.sky.utility.*;

public final class DateUtils {
	
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd MMM yyyy");
	private static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("dd MMM yyyy HH:mm");
	
	private DateUtils() {
		
	}
	
	public static void main(String[] args) {
		System.out.println("3: " + toHourMinute(3));
		System.out.println("0: " + toHourMinute(0));
		System.out.println("100: " + toHourMinute(100));
		System.out.println("9999: " + toHourMinute(9999));
		
		System.out.println("date: " + constructDatetime(new Date(), "1103"));
		System.out.println("date: " + constructDatetime(new Date(), null));
		System.out.println("date: " + constructDatetime(new Date(), "3"));
	}
	
	/**
	 * construct the datetime object 
	 * @param date
	 * @param timeNumber HH + MI, however, it was originally a numeric data, so 00:02 just as 2
	 * @return return datetime object if date & time number is not null.
	 */
	public static Date constructDatetime(Date date, String hourMinute) {
		if (null == date) {
			return null;
		} else {
			// parse the time format to be HH:MI
			int hmNumber = ValueUtils.toInt(hourMinute);
			String hm = toHourMinute(hmNumber);
			
			// construct the date & time
			String dateStr = DATE_FORMAT.format(date);
			try {
				return DATETIME_FORMAT.parse(dateStr + " " + hm);
			} catch (ParseException e) {
				return null;
			}
		}
	}
	
	public static String toHourMinute(int number) {
		boolean invalid = NullNumberInterpreter.getInstance().isNull(number) ||
			number < 0 || number > 9999;
		if (invalid) {
			return "00:00";
		} else {
			NumberFormat format = new DecimalFormat("#00");
			String hour = format.format(number / 100);
			String minute = format.format(number % 100);
			return hour + ":" + minute;
		}
	}
	
}

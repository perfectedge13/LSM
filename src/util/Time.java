package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
	
	public static long currentEpoch() {
		return System.currentTimeMillis();
	}
	
	public static String getFormattedTime(long epoch) {
		
		Date date = new Date(epoch);
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss z");
		String formattedDate = sdf.format(date);
		
		return formattedDate;
		
	}

}

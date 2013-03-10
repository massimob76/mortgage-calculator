package mortgage;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Helper {
	
	public static final DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
	
	public static Calendar date(String when) throws ParseException {
		Date date = df.parse(when);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	public static JsonElement getJsonElement(String json) {
		return new JsonParser().parse(json.replace("'", "\""));
	}

}

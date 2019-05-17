package retrofit;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDeserializer implements JsonDeserializer<Date> {
	@Override
	public Date deserialize(JsonElement json, Type typeOfT,
	                        JsonDeserializationContext context) throws JsonParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Date returnDate = null;
		try {
			String jsonDate = json.getAsString();
			
			if (jsonDate == null) return null;
			
			if (jsonDate.isEmpty()) return null;
			
			returnDate =  format.parse(jsonDate);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		return returnDate;
	}
}

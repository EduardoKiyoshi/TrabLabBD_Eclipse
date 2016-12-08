package util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TimeUtil {
	private static DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("HH:mm:ss", Locale.US);
	public static String format(LocalTime date) {
        if (date == null) {
            return null;
        }
        return formatter.format(date);
    }
}

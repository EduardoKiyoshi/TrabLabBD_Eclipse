package util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TimeUtil {
	private static DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("HH:mm", Locale.US);
	public static String format(LocalTime time) {
        if (time == null) {
            return null;
        }
        return formatter.format(time);
    }
}

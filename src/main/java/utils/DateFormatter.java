package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalDate.now;

public class DateFormatter {

  private static Map<String, String> months = new HashMap<String, String>() {{
    put("июня", "06");
    put("июля", "07");
    put("августа", "08");
    put("сентября", "09");
    put("октября", "10");
    put("ноября", "11");
    put("декабря", "12");
    put("января", "01");
    put("февраля", "02");
    put("марта", "03");
    put("апреля", "04");
    put("мая", "05");
  }};

  public static Date getDateFromString(String string) {
    String[] datesString = string.split(" ");
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    try {
      return format.parse(datesString[0] + "." + months.get(datesString[1]) + "." + getYear(datesString[1]));
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  private static String getYear(String s) {
    LocalDate date = now();
    int month = date.getMonthValue();
    if (Integer.parseInt(months.get(s)) < month) {
      return Integer.toString(date.getYear() + 1);
    } else {
      return Integer.toString(date.getYear());
    }
  }
}

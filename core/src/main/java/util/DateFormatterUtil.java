package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatterUtil {

    public static LocalDateTime format(String date) {
        //      Defining the formatter for the original data-time format, which received from the user end as a local date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
//      Parse the original date into a LocalDateTime
        LocalDateTime originalDateTime = LocalDateTime.parse(date, formatter);
//      defining the desired formatter
        DateTimeFormatter desiredFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        format the localdatetime object to the desired formatter. now it will be string verion of the sql datetime.
        String formattedDateTimeString = originalDateTime.format(desiredFormatter);
        System.out.println("format "+formattedDateTimeString);
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime formattedExpectedDate = LocalDateTime.parse(formattedDateTimeString, formatter);
        return formattedExpectedDate;
    }
}

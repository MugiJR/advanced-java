package enums;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyDate {

    int year;
    Month month;
    int day;

    public MyDate(int year, Month month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    Weekday getWeekday() {
        var gc = new GregorianCalendar(year, month.ordinal(), day);
        int idx = gc.get(Calendar.DAY_OF_WEEK);
        System.out.println(idx);
        return Weekday.values()[idx];
    }



}

package enums;

import java.util.Arrays;
import java.util.Date;

public class MyDate1 {

    private static final String[] MONTH_ENGLISH = {"January","February","March"};
    private static final String[] MONTH_MAGYAR =  {"januar", "februar", "marcius"};

    int day, year, month;

    public enum Month {
        JAN, FEB, MAR;

        String getEnglishName() {
            return MONTH_ENGLISH[this.ordinal()];
        }

        String getHunName() {
            return MONTH_MAGYAR[this.ordinal()];
        }

    };

    public enum Weekday {
        MON, TUE, WED, THU, FRI, SAT, SUN

    };


    public static void main(String[] args) {
        System.out.println("===English Names===");
        Arrays.stream(Month.values()).forEach(m -> System.out.println(m.getEnglishName()));
        System.out.println("\n===Hungarian Names===");
        Arrays.stream(Month.values()).forEach(m -> System.out.println(m.getHunName()));

    }
}

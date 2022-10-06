package lamdas;

//================================
// [TASK 1] Make a lambda that takes a day of the week and a number n, and returns the weekday that is n days after the other one.
//Keep in mind that n can be a big number, or a negative one.
//================================
import enums.Weekday;

import java.util.function.BiFunction;



public class WeekdayStepper {



    public static void main(String[] args) {

        BiFunction<Weekday, Integer, Weekday> nextWeekDay =
                (weekday, n) -> {
                    final int weekLength = Weekday.values().length;
                    int nextWeekIdx = weekday.ordinal() + (n % weekLength);
                    nextWeekIdx += (nextWeekIdx >= 0) ? 0 : weekLength;
                    return Weekday.values()[nextWeekIdx];
                };

        System.out.println(nextWeekDay.apply(Weekday.MON,-1));

    }
}

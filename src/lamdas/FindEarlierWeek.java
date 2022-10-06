package lamdas;

//[Task 2]
//Make a lambda that takes two weekdays, and returns whether the first one is earlier in the week than the second one.

import enums.Weekday;

import java.util.function.BiFunction;

public class FindEarlierWeek {
    public static void main(String[] args) {

        BiFunction<Weekday, Weekday, Boolean> findEarlierWeekDay =
                (fWeekday, sWeekday) -> fWeekday.ordinal() <= sWeekday.ordinal();

        System.out.println(findEarlierWeekDay.apply(Weekday.MON, Weekday.WED));
        System.out.println(findEarlierWeekDay.apply(Weekday.SUN, Weekday.SAT));
        System.out.println(findEarlierWeekDay.apply(Weekday.FRI, Weekday.THU));
    }
}

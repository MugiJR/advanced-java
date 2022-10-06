package lamdas;

//=================================================================
// [Task 3]
// Make a lambda that, when called, returns a day of the week;
// when called again and again, it produces the days of the week in order. If called even more times, it starts at the first day again
//=================================================================


import enums.Weekday;

import java.util.function.Supplier;

public class ReturnDayInOrder {

    private static int currentDayIdx = 0;

    static Weekday printDayOfTheWeek() {
        if (currentDayIdx == Weekday.values().length) currentDayIdx = 0;
        return Weekday.values()[currentDayIdx++];
    }

    public static void main(String[] args) {

        Supplier<Weekday> getNextWeekday = ReturnDayInOrder::printDayOfTheWeek;
        for(int i = 0; i < 18; i++) {
            System.out.println(getNextWeekday.get());
        }
    }

}

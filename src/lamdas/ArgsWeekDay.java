package lamdas;

// [TASK 3]
// Sort the command line arguments so that those arguments that contains names of weekdays come first

import enums.Weekday;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

public class ArgsWeekDay {
    public static void main(String[] args) {
        args = new String[]{"aaa", "SUN","mugesheae", "MON", "asdfa","SAT", "TUE", "WED","bbasdfa","ccc"};

        Predicate<String> isWeekDay = d -> {
            for (var i : Weekday.values()) {
                if ( d.equals(i.toString())) return true;
            }
            return false;
        };

        Function<String, Integer> findWeekDay = day -> !isWeekDay.test(day) ? 1 : -1;

        Comparator<String> cmp = (day1, day2) -> findWeekDay.apply(day1) - findWeekDay.apply(day2);

        System.out.println("Before Sort = " + Arrays.toString(args));
        Arrays.sort(args, cmp);
        System.out.println("After Sort = " + Arrays.toString(args));
    }
}

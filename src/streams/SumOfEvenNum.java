package streams;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    Print the sum of even numbers that are greater than 8 from the command line arguments.
        -- Variant: ignore all texts that are not numbers.
        -- Variant: using a lambda, take the condition as an argument.
            So, instead of testing for “greater than 8”, test whatever the caller specifies.

                Input  : 1, 5, 7, 4, 8, Two, 14, 21, 20, -10
                Output : 34
 */
public class SumOfEvenNum {

    private static int findSum(String[] args, Predicate<Integer> f) {
        Predicate<Integer> isEven = num -> num % 2 == 0;
        return Arrays.stream(args)
                .filter(s -> s.matches("[0-9]+"))
                .mapToInt(Integer::parseInt)
                .filter(isEven::test)
                .filter(f::test)        // .filter(num -> isEven.and(f).test(num))
                .sum();
    }



    public static void main(String[] args) {
        args = Stream.of("1","5", "7", "4", "8", "Two", "14", "21", "20", "-10").toArray(String[]::new);
        System.out.println("The sum = "+ findSum(args, i -> i > 8));
    }
}

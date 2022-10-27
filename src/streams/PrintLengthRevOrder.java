package streams;

/*
 Print the lengths of the command line arguments in reverse order.

 Input  : aa, aaa, aaaa, a, bbbbb
 Output : 5, 4, 3, 2, 1
*/


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PrintLengthRevOrder {

    public static void main(String[] args) {
        args = new String[]{"aa", "aaa", "aaaa", "a", "bbbbb"};

        // My Solution
        Arrays.stream(args)
                .map(String::length)
                .sorted(Collections.reverseOrder())
                .forEach(System.out::println);

        // Professor's Solution
        Arrays.stream(args)
                .map(String::length)              // ------> Stream<Integer>
                //.mapToInt(String::length)       // ----> IntStream
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
}


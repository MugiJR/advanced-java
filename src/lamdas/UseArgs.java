package lamdas;

// [TASK 2]
// Sort the command line arguments based on the number of 'a' characters in them.

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

public class UseArgs {

    public static void main(String[] args) {

        Function<String, Integer> countAs = str -> str.replaceAll("[^a]","").length();

        Comparator<String> cmpAs = (s1, s2) -> countAs.apply(s2) - countAs.apply(s1);

        System.out.println("Before Sort = " + Arrays.toString(args));
        Arrays.sort(args, cmpAs);
        // We can use Arrays.sort(args, Comparator.comparing(countAs::apply));
        System.out.println("Before Sort = " + Arrays.toString(args));



    }
}

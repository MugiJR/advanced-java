package streams;

import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class PrimeNumbers {

    private static void generatePrimeNums(int num) {

        IntStream.iterate(2, n->n+1)
                .filter(p->IntStream.range(2,p)
                .noneMatch(div->p%div == 0))
                .limit(num)
                .forEach(System.out::println);

    }

    public static void main(String[] args) {
        System.out.println("First 5 prime numbers \n");
        generatePrimeNums(5);
        System.out.println("\n First 10 prime numbers \n");
        generatePrimeNums(10);
    }
}

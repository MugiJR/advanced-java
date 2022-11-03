package streams;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class FileReader {

    private static int linesInFile(String filename) {
       try{
           return (int) Files.lines(Path.of(filename)).count();
       } catch (IOException e) {
           return 0;
       }
    }

    private static int wordsOnFirstLine(String filename) {
        try{
            return (int) Files.lines(Path.of(filename))
                    .findFirst()
                    .map(line -> line.split(" ").length)
                    .orElse(0);
        } catch (IOException e) {
            return 0;
        }
    }

    public static void main(String[] args) {
        args = "src//fib//Fib.java src//fib//FibTest.java".split( " ");
        String result =
                Arrays.stream(args)
                        .sorted(Comparator.comparing((String filename) -> linesInFile(filename)).thenComparing(filename -> wordsOnFirstLine(filename)))
                        .collect(Collectors.joining(", "));
        System.out.println(result);
    }
}

package lamdas;
/*
[TASK 4]
Create the method createArray that fills in an array.
It takes the length of the array as an argument, and an int→int function f.
Use the f function on each index of the array, and it will produce the value for the index.

[SUB TASKS]
-- Make an array of arrays in a similar way. Here, the function has to take two arguments for the two indices.
-- Can you make createArray generate an array of arbitrary depth?
 */

import java.util.Arrays;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

public class CreateArray {

    public static void main(String[] args) {
        BiFunction<Integer, Function<Integer, Integer>, int[]> createArray = (n, f) -> {
                var arr = new int[n];
                for (int idx = 0; idx < n; idx++) {
                    arr[idx] = f.apply(idx);
                }
                return arr;
        };

        BiFunction<Map.Entry<Integer, IntUnaryOperator>, IntBinaryOperator, int[][]> createArrOfArray =
                (arrSetup, f) -> {
                    int n = arrSetup.getKey(); // n holds the array size
                    int[][] arr = new int[n][];
                    IntUnaryOperator getRowsize = arrSetup.getValue(); // getRowsize holds function which return rowsize

                    for (int row = 0; row < n; row++) {
                        int colSizeForTheCurrentRow = getRowsize.applyAsInt(row);
                        arr[row] = new int[colSizeForTheCurrentRow];
                        for (int col = 0; col < colSizeForTheCurrentRow; col++) {
                            arr[row][col] = f.applyAsInt(row, col);
                        }
                    }

                    return arr;
                };

        System.out.println(Arrays.toString(createArray.apply(6, n->n*2+1)));
        System.out.println(Arrays.deepToString(createArrOfArray.apply(Map.entry(5, row->row*3),(i, j) -> 10*i+j)));
    }


}

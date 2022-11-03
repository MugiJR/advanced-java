package fib;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static fib.Fib.*;
import static org.junit.jupiter.api.Assertions.*;

public class FibTest {


    @Test
    public void fib0() {
        assertEquals(1, fib(0));
    }

    @Test
    public void fibException() {
        IllegalArgumentException exc = assertThrows(IllegalArgumentException.class, () -> fib(-1));
        assertEquals("Negative...", exc.getMessage());
    }

    @ParameterizedTest(name = "fib({0}) = {1}")
    @CsvSource({"0,1","1,1","5,8"})
    public void fibPar(int n, int expected) {
        assertEquals(expected, n);
    }



}

package lamdas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static lamdas.Train.*;

public class TrainTest {

    @Test
   public void test() {
        Function<Map.Entry<Integer,Integer>, Stream<Train>> useTrainResult = useTrains11(makeHourlies.apply(3, 20),  isTimeBetween,10);
        Stream<Train> trains = useTrainResult.apply(Map.entry(10, 12));
        var expectedTrains = new ArrayList<String>(Arrays.asList("Hourly10","Hourly11"));
        var actualyTrains = trains.map(s->s.getName()).collect(Collectors.toList());
        assertIterableEquals(expectedTrains, actualyTrains);
    }

}

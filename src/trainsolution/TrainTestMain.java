package trainsolution;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;


import static trainsolution.Train.*;

public class TrainTestMain {
    public static void main(String[] args) {
        Function<Map.Entry<Integer,Integer>, Stream<Train>> useTrainResult =
                useTrains11(makeHourlies.apply(3, 20),isTimeBetween,10);
        Stream<Train> trains = useTrainResult.apply(Map.entry(10, 12));
        trains.forEach(s->System.out.println(s.getName()));
    }
}

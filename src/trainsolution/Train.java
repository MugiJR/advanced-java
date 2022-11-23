package trainsolution;

import java.io.Serializable;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Train implements Serializable {

    private static final long serialVersionUID = 1L;
    private int departHour, arriveHour;
    private String name;

    public Train(int arriveHour, int departHour, String name ) {
        this.departHour = departHour;
        this.arriveHour = arriveHour;
        this.name = name;
    }

    public int getDepartHour() {
        return departHour;
    }


    public int getArriveHour() {
        return arriveHour;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return String.format("ThisIsThe%s[%o-%o]",this.name, this.arriveHour, this.departHour);
    }


    public static Function<Map.Entry<Integer, Integer>, Stream<Train>> useTrains11(
            Stream<String> textStream,
            BiFunction<Train, Map.Entry<Integer, Integer>, Boolean> condition,
            int maxTrains
    ) {
        var result = textStream.map(Train::convertStrToTrain)
                .filter(t -> condition.apply(t,Map.entry(t.getArriveHour(),t.getDepartHour())))
                .limit(maxTrains);

        return new Function<Map.Entry<Integer, Integer>, Stream<Train>>() {
            @Override
            public Stream<Train> apply(Map.Entry<Integer, Integer> integerIntegerEntry) {
                return result.filter(r-> Train.isTimeBetween.apply(r,integerIntegerEntry));
            }
        };
    }


    public static BiFunction<Train, Map.Entry<Integer,Integer>, Boolean> isTimeBetween = (train, range) -> {
        final int arrivalRange = range.getKey();
        final int departRange = range.getValue();
        final int trainArrival = train.getArriveHour();
        final int trainDepart = train.getDepartHour();

        return (arrivalRange <= trainArrival &&
                trainArrival < departRange &&
                trainDepart <= departRange);

    };

    public static BiFunction<Integer,Integer,Stream<String>> makeHourlies = (minHour, maxHour) -> {
        return IntStream.range(minHour,maxHour).mapToObj(i -> makeHourliersHelper(i,i+1));
    };


    private static Train convertStrToTrain(String txt) {
        var result = txt.split(",");
        return new Train(Integer.parseInt(result[0]), Integer.parseInt(result[1]), result[2]);
    }

    private static String makeHourliersHelper(int a, int d) {
        return a +","+d+","+"Hourly"+a;
    }
}

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static Stream<Map.Entry<Integer, Long>> frequencyMap(Stream<Integer> numbers) {
        return
                numbers.collect(
                    Collectors.groupingBy(
                            Function.identity(),
                            Collectors.counting()
                    )
                ).entrySet().stream();
    }

    public static void main( String args[] ) {
        List<Integer> numbers = Arrays.asList(1,1,2,2,2,3,4,5,5,5,5,1,1,1);
        Map.Entry<Integer, Long> numbersFrequency =
                frequencyMap(numbers.stream()).max(Comparator.comparing(Map.Entry::getValue))
                        .orElse(null);
        if(numbersFrequency != null)
            System.out.println(
                    String.format("Plateau : %d, Occurrence : %s",
                            numbersFrequency.getKey(), numbersFrequency.getValue()));
    }
}

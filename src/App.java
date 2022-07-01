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

    public static <K, V extends Comparable<V> > Map<K, V> valueSort(final Map<K, V> map) {
        // Static Method with return type Map and
        // extending comparator class which compares values
        // associated with two keys
        // return comparison results of values of
        // two keys
        Comparator<K> valueComparator = (k1, k2) -> {
            int comp = map.get(k1).compareTo(
                    map.get(k2));
            if (comp == 0)
                return 1;
            else
                return comp;
        };

        // SortedMap created using the comparator
        Map<K, V> sorted = new TreeMap<K, V>(valueComparator);
        sorted.putAll(map);

        return sorted;
    }


        public static void main( String args[] ) {
        List<Integer> numbers = Arrays.asList(7,7, 2, 1, 1, 2, 2, 2, 3, 4, 5, 5, 5, 5, 1, 1, 1, 5, 5, 5, 2, 1, 1);
        Map.Entry<Integer, Long> numbersFrequency =
                frequencyMap(numbers.stream()).max(Comparator.comparing(Map.Entry::getValue))
                        .orElse(null);
        if(numbersFrequency != null)
            System.out.println(
                    String.format("Plateau : %d, Occurrence : %s",
                            numbersFrequency.getKey(), numbersFrequency.getValue()));

        System.out.println("\nSecond Method");
        Map<Integer, Long> map = numbers.stream().collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        Comparator<Integer> valueComparator = (k1, k2) -> {
            int comp = - map.get(k1).compareTo(
                    map.get(k2));
            if (comp == 0)
                return 1;
            else
                return comp;
        };
        Map<Integer, Long> sorted = new TreeMap<>(valueComparator);
        sorted.putAll(map);
        System.out.println(sorted);
        Map.Entry<Integer, Long> entry = sorted.entrySet().iterator().next();
        Integer key = entry.getKey();
        Long value = entry.getValue();
        System.out.println(
                String.format("Plateau : %d, Occurrence : %d",
                        key, value));
        // SortedMap created using the comparator
        // Map<Integer, Long> sorted = valueSort(map);
        // System.out.println(sorted);

        System.out.println("\nThird Method");
        Map<Integer, List<Integer>> mymap = numbers.stream().collect(Collectors.groupingBy(
                Function.identity()
        ));
        Comparator<Integer> listSizeComparator = (k1, k2) -> {
            Integer k1Size = mymap.get(k1).size();
            Integer k2Size = mymap.get(k2).size();
            int comp = - k1Size.compareTo(k2Size);
            if(comp == 0)
                return 1;
            else
                return comp;
        };
        Map<Integer, List<Integer>> sortedMyMap = new TreeMap<>(listSizeComparator);
        sortedMyMap.putAll(mymap);
        System.out.println(sortedMyMap);
        Map.Entry<Integer, List<Integer>> entrySorted = sortedMyMap.entrySet().iterator().next();
        System.out.println(entrySorted);

    }
}

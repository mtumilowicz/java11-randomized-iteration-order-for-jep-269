import org.junit.Test;

import java.util.*;

import static java.util.Map.*;

/**
 * Created by mtumilowicz on 2018-11-26.
 */
public class RandomIterationOrderTest {

    @Test
    public void set_jep269() {
        Set.of(1, 2, 3, 4, 5).forEach(System.out::println);
    }

    @Test
    public void set_jep269_multiple_one_jvm_instance() {
        Set.of(1, 2, 3, 4, 5).forEach(System.out::println);
        System.out.println("---");
        Set.of(1, 2, 3, 4, 5).forEach(System.out::println);
        System.out.println("---");
        Set.of(1, 2, 3, 4, 5).forEach(System.out::println);
    }

    @Test
    public void old_hashSet() {
        new HashSet<>(Arrays.asList(1, 2, 3, 4, 5)).forEach(System.out::println);
    }

    @Test
    public void map_jep269() {
        Map.ofEntries(
                entry(1, 1),
                entry(2, 2),
                entry(3, 3),
                entry(4, 4),
                entry(5, 5)
        ).forEach((k, v) -> System.out.println(k + ": " + v));
    }
    
    @Test
    public void old_hashMap() {
        var map = new HashMap<Integer, Integer>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        map.put(5, 5);
        
        map.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}

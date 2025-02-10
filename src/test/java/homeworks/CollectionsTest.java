package homeworks;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class CollectionsTest {

    private static final Logger log = LoggerFactory.getLogger(CollectionsTest.class);

    @Test
    void testCollections() {
        ArrayList<Integer> arrayList = new ArrayList<>() {{
            add(1);
            add(7);
            add(3);
            add(5);
            add(9);
        }};
        ArrayList<Double> arrayListCapacity = new ArrayList<>(15);
        ArrayList<Double> arrayListByList = new ArrayList<>(arrayListCapacity);

        HashMap<String, Integer> hashMap = new HashMap<>();
        HashMap<String, Integer> hashMapInitial = new HashMap<>(12);
        HashMap<String, Integer> hashMapInitialLoad = new HashMap<>(32, 23);
        HashMap<String, Integer> hashMapByMap = new HashMap<>(new HashMap<>(43, 23));

        TreeSet<String> treeSet = new TreeSet<>();
        TreeSet<String> treeSetComparator = new TreeSet<>(String::compareTo);
        TreeSet<String> treeSetByCollection = new TreeSet<>(new ArrayList<>() {{
            add("sad");
            add("bad");
        }});
        TreeSet<String> treeSetBySortedSet = new TreeSet<>(new TreeSet<>() {{
            add("sad");
            add("bad");
        }});

        log.debug("Original array: {}", arrayList);
        Collections.sort(arrayList);
        log.debug("Sorted array: {}", arrayList);
        Collections.reverse(arrayList);
        log.debug("Reversed array: {}", arrayList);
        Collections.shuffle(arrayList);
        log.debug("Shuffled array: {}", arrayList);
        Collections.rotate(arrayList, 3);
        log.debug("Rotated array: {}", arrayList);
        Collections.swap(arrayList, 0, 2);
        log.debug("Swapped array: {}", arrayList);
        Collections.fill(arrayList, 5);
        log.debug("Fill array: {}", arrayList);
        Collections.addAll(arrayList, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        log.debug("Array with added elements: {}", arrayList);
        Collections.replaceAll(arrayList, 5, 2);
        log.debug("Replaced array: {}", arrayList);
        Integer max = Collections.max(arrayList);
        log.debug("Max in array: {}", max);
        Integer min = Collections.min(arrayList);
        log.debug("Min in array: {}", min);
        int frequency = Collections.frequency(arrayList, 2);
        log.debug("Frequency 2 in array: {}", frequency);
    }
}

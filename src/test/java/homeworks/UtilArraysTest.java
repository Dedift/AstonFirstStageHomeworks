package homeworks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

class UtilArraysTest {
    private static final Logger log = LoggerFactory.getLogger(UtilArraysTest.class);
    int[] array;

    @BeforeEach
    void setUp() {
        this.array = new int[]{4, 3, 6, 2, 3, 5, 4, 8, 6, 4, 7};
    }

    @Test
    void isSorted() {
        log.debug("Original array: {}", Arrays.toString(array));
        boolean isSorted = UtilArrays.isSorted(array);
        log.debug("Array is sorted: {}", isSorted);
        Assertions.assertFalse(isSorted);
    }

    @Test
    void swapFirstAndLast() {
        log.debug("Original array: {}", Arrays.toString(array));
        int[] swappedArray = UtilArrays.swapFirstAndLast(array);
        log.debug("Swapped array: {}", Arrays.toString(swappedArray));
        Assertions.assertArrayEquals(new int[]{7, 3, 6, 2, 3, 5, 4, 8, 6, 4, 4}, swappedArray);
    }

    @Test
    void firstUniqueNumber() {
        log.debug("Original array: {}", Arrays.toString(array));
        Assertions.assertEquals(2, UtilArrays.firstUniqueNumber(array));
    }

    @Test
    void mergeSort() {
        log.debug("Original array: {}", Arrays.toString(array));
        int[] sortedArray = UtilArrays.mergeSort(array, 0, array.length - 1);
        log.debug("Sorted array: {}", Arrays.toString(sortedArray));
        Assertions.assertArrayEquals(new int[]{2, 3, 3, 4, 4, 4, 5, 6, 6, 7, 8}, sortedArray);
    }
}
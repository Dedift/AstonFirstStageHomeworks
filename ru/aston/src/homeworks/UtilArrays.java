package homeworks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class UtilArrays {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }

    private static final Logger log = LoggerFactory.getLogger(UtilArrays.class);

    private UtilArrays() {
    }

    public static boolean isSorted(int[] array) {
        if (array.length < 1) {
            log.warn("Array is empty");
        }

        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                log.debug("Please try again");
                return false;
            }
        }

        log.debug("Ok");
        return true;
    }

    public static int[] swapFirstAndLast(int[] array) {
        if (array.length < 1) {
            log.warn("Array is empty");
        }

        int firstNumber = array[0];
        log.debug("First number in array: " + firstNumber);
        int lastNumber = array[array.length - 1];
        log.debug("Last number in array: " + lastNumber);
        array[0] = lastNumber;
        array[array.length - 1] = firstNumber;
        log.debug("Swap first number={}, and last number={}", firstNumber, lastNumber);
        return array;
    }

    public static int firstUniqueNumber(int[] array) {
        if (array.length < 1) {
            log.warn("Array is empty");
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < array.length - 1; i++) {
            map.put(array[i], map.getOrDefault(array[i], 0) + 1);
            log.debug("Put number in map: {}", array[i]);
        }

        for (int i = 0; i < array.length - 1; i++) {
            if (map.get(array[i]) == 1) {
                log.debug("First unique number: {}", array[i]);
                return array[i];
            }
        }

        log.debug("There are no unique numbers");
        return -1;
    }

    public static int[] mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            log.debug("Calculated middle index: {}", mid);
            log.debug("Sorting left subarray: left index={}, right index={}", left, mid);
            mergeSort(array, left, mid);
            log.debug("Sorting right subarray: left index={}, right index={}", mid + 1, right);
            mergeSort(array, mid + 1, right);
            log.debug("Merging subarrays: left index={}, middle index={}, right index={}", left, mid, right);
            merge(array, left, mid, right);
        }
        return array;
    }

    private static void merge(int[] originalArray, int leftIndex, int midIndex, int rightIndex) {
        int leftArraySize = midIndex - leftIndex + 1;
        int rightArraySize = rightIndex - midIndex;
        log.debug("Left subarray size: {}", leftArraySize);
        log.debug("Right subarray size: {}", rightArraySize);

        int[] leftArray = new int[leftArraySize];
        int[] rightArray = new int[rightArraySize];

        for (int i = 0; i < leftArraySize; ++i) {
            leftArray[i] = originalArray[leftIndex + i];
            log.debug("Copied to left subarray: element={}, index={}", leftArray[i], i);
        }
        log.debug("Left subarray: {}", Arrays.toString(leftArray));

        for (int j = 0; j < rightArraySize; ++j) {
            rightArray[j] = originalArray[midIndex + 1 + j];
            log.debug("Copied to right subarray: element={}, index={}", rightArray[j], j);
        }
        log.debug("Right subarray: {}", Arrays.toString(rightArray));

        int leftArrayIterator = 0, rightArrayIterator = 0;
        int originalArrayIterator = leftIndex;

        while (leftArrayIterator < leftArraySize && rightArrayIterator < rightArraySize) {
            if (leftArray[leftArrayIterator] <= rightArray[rightArrayIterator]) {
                originalArray[originalArrayIterator] = leftArray[leftArrayIterator];
                log.debug("Merged from left subarray: element={}, index={}", leftArray[leftArrayIterator], originalArrayIterator);
                leftArrayIterator++;
            } else {
                originalArray[originalArrayIterator] = rightArray[rightArrayIterator];
                log.debug("Merged from right subarray: element={}, index={}", rightArray[rightArrayIterator], originalArrayIterator);
                rightArrayIterator++;
            }
            originalArrayIterator++;
        }

        while (leftArrayIterator < leftArraySize) {
            originalArray[originalArrayIterator] = leftArray[leftArrayIterator];
            log.debug("Copied remaining from left subarray: element={}, index={}", leftArray[leftArrayIterator], originalArrayIterator);
            leftArrayIterator++;
            originalArrayIterator++;
        }

        while (rightArrayIterator < rightArraySize) {
            originalArray[originalArrayIterator] = rightArray[rightArrayIterator];
            log.debug("Copied remaining from right subarray: element={}, index={}", rightArray[rightArrayIterator], originalArrayIterator);
            rightArrayIterator++;
            originalArrayIterator++;
        }

        log.debug("Merged array: {}", originalArray);
    }
}

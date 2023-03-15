package task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SelectionSorterTest {

    @Test
    void unsortedArrayTest() {
        int [] arr = {2, 5, 10, 4, 7, 88, 10, 0};
        int [] expected = {0, 2, 4, 5, 7, 10, 10, 88};
        Assertions.assertArrayEquals(expected, SelectionSorter.sort(arr));
    }

    @Test
    void equalArrayElementsTest() {
        int [] arr = {0, 0, 0, 0, 0};
        int [] expected = {0, 0, 0, 0, 0};
        Assertions.assertArrayEquals(expected, SelectionSorter.sort(arr));
    }

    @Test
    void sortedArrayTest() {
        int [] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int [] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Assertions.assertArrayEquals(expected, SelectionSorter.sort(arr));
    }

    @Test
    void negativeElementsArrayTest() {
        int [] arr = {-2, -5, 10, 4, -7, -88, 10, 0};
        int [] expected = {-88, -7, -5, -2, 0, 4, 10, 10};
        Assertions.assertArrayEquals(expected, SelectionSorter.sort(arr));
    }

    @Test
    void twoElementsTest() {
        int [] arr = {13, 9};
        int [] expected = {9, 13};
        Assertions.assertArrayEquals(expected, SelectionSorter.sort(arr));
    }

    @Test
    void oneElementTest() {
        int [] arr = {7};
        int [] expected = {7};
        Assertions.assertArrayEquals(expected, SelectionSorter.sort(arr));
    }

    @Test
    void emptyArrayTest() {
        int [] arr = {};
        int [] expected = {};
        Assertions.assertArrayEquals(expected, SelectionSorter.sort(arr));
    }

    @Test
    void minAndMaxIntArrayTest() {
        int [] arr = {Integer.MAX_VALUE, Integer.MIN_VALUE};
        int [] expected = {Integer.MIN_VALUE, Integer.MAX_VALUE};
        Assertions.assertArrayEquals(expected, SelectionSorter.sort(arr));
    }

}
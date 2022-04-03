package me.sonofsoil.code.algorithm;

import static org.apache.commons.lang3.ArrayUtils.toObject;
import static org.apache.commons.lang3.ArrayUtils.toPrimitive;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.ArrayMatching.arrayContaining;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Arrays;

public class MaxStableSubarrayFinderTest {

    @DataProvider(name="provideTestParams")
    public static Object[][] provideTestParams() {
        return new Object[][] {
                {new Integer[] {5}, 10, new Integer[] {5}},
                {new Integer[] {1, 2, 6, 4, 3, 5, 9, 8, 10}, 2, new Integer[] {4, 3, 5}},
                {new Integer[] {1, 2, 6, 4, 3, 5, 9, 8, 10}, 4, new Integer[] {2, 6, 4, 3, 5}},
                {new Integer[] {1, 2, 6, 4, 3, 5, 9, 8, 10}, 20, new Integer[] {1, 2, 6, 4, 3, 5, 9, 8, 10}},
                {new Integer[] {1, 2, 6, 4, 3, 5, 9, 8, 10}, 1, new Integer[] {1, 2}},
                {new Integer[] {1, 2, 6, 4, 3, 5, 9, 8, 10}, 0, new Integer[] {1}}
        };
    }

    @Test(dataProvider = "provideTestParams")
    public void testMany(Integer[] input, int distance, Integer[] expected) {
        MaxStableSubarrayFinder inst = new MaxStableSubarrayFinder();
        int[] actual = inst.findMaxStableSubarray(toPrimitive(input), distance);
        System.out.println("output array: " + Arrays.toString(actual));
        assertThat(toObject(actual), is(arrayContaining(expected)));
    }

    @Test
    public void testNullInput() {
        MaxStableSubarrayFinder inst = new MaxStableSubarrayFinder();
        int[] actual = inst.findMaxStableSubarray(null, 1);
        Assert.assertNull(actual);
    }

    @Test
    public void testEmptyInput() {
        MaxStableSubarrayFinder inst = new MaxStableSubarrayFinder();
        int[] actual = inst.findMaxStableSubarray(new int[0], 2);
        assertThat(toObject(actual), emptyArray());
    }
}

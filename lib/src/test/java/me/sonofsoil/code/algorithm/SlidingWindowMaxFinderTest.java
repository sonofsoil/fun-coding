package me.sonofsoil.code.algorithm;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.apache.commons.lang3.ArrayUtils.toObject;
import static org.apache.commons.lang3.ArrayUtils.toPrimitive;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.ArrayMatching.arrayContaining;

public class SlidingWindowMaxFinderTest {

    @DataProvider(name="provideTestParams")
    public static Object[][] provideTestParams() {
        return new Object[][] {
                {new Integer[] {5}, 1, new Integer[] {5}},
                {new Integer[] {1, 2, 6, 4, 3, 5, 9, 8, 10}, 3, new Integer[] {6, 6, 6, 5, 9, 9, 10}},
                {new Integer[] {1, 2, 6, 4, 3, 5, 9, 8, 10}, 4, new Integer[] {6, 6, 6, 9, 9, 10}},
                {new Integer[] {1, 2, 6, 4, 3, 5, 9, 8, 10}, 8, new Integer[] {9, 10}},
                {new Integer[] {1, 2, 6, 4, 3, 5, 9, 8, 10}, 1, new Integer[] {1, 2, 6, 4, 3, 5, 9, 8, 10}},
                {new Integer[] {1, 2, 6, 4, 3, 5, 9, 8, 10}, 2, new Integer[] {2, 6, 6, 4, 5, 9, 9, 10}}
        };
    }

    @Test(dataProvider = "provideTestParams")
    public void testSlidingWindowMaxFinder(Integer[] input, int window, Integer[] expected) {
        SlidingWindowMaxFinder inst = new SlidingWindowMaxFinder();
        int[] actual = inst.findSlidingWindowMaxValues(toPrimitive(input), window);
        System.out.println("output array: " + Arrays.toString(actual));
        assertThat(toObject(actual), is(arrayContaining(expected)));
    }

    @Test
    public void testSlidingWindowMaxFinderEmptyInput() {
        SlidingWindowMaxFinder inst = new SlidingWindowMaxFinder();
        int[] actual = inst.findSlidingWindowMaxValues(new int[]{}, 2);
        Assert.assertEquals(actual.length, 0);
    }

    @Test
    public void testSlidingWindowMaxFinderNullInput() {
        SlidingWindowMaxFinder inst = new SlidingWindowMaxFinder();
        int[] actual = inst.findSlidingWindowMaxValues(null, 10);
        Assert.assertNull(actual);
    }
}

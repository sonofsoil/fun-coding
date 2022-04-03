package me.sonofsoil.code.algorithm;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.apache.commons.lang3.ArrayUtils.toObject;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.ArrayMatching.arrayContainingInAnyOrder;

public class SteppingNumbersFinderTest {

    @DataProvider(name="provideTestParams")
    public static Object[][] provideTestParams() {
        return new Object[][] {
                {-3, new Integer[] {}},
                {0, new Integer[] {}},
                {1, new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9}},
                {2, new Integer[] {10, 12, 21, 23, 32, 34, 43, 45, 54, 56, 65, 67, 76, 78, 87, 89, 98}},
                {
                        3,
                        new Integer[] {
                                101, 121, 123, 210, 212, 232, 234, 321, 323, 343, 345, 432, 434, 454, 456, 543, 545,
                                565, 567, 654, 656, 676, 678, 765, 767, 787, 789, 876, 878, 898, 987, 989
                        }
                },
                {
                        4,
                        new Integer[] {
                                1010, 1012, 1210, 1212, 1232, 1234, 2101, 2121, 2123, 2321, 2323, 2343, 2345, 3210,
                                3212, 3232, 3234, 3432, 3434, 3454, 3456, 4321, 4323, 4343, 4345, 4543, 4545, 4565,
                                4567, 5432, 5434, 5454, 5456, 5654, 5656, 5676, 5678, 6543, 6545, 6565, 6567, 6765,
                                6767, 6787, 6789, 7654, 7656, 7676, 7678, 7876, 7878, 7898, 8765, 8767, 8787, 8789,
                                8987, 8989, 9876, 9878, 9898
                        }
                },
        };
    }

    @Test(dataProvider = "provideTestParams")
    public void testFindSteppingNumbersInRange(int length, Integer[] expected) {
        SteppingNumbersFinder inst = new SteppingNumbersFinder();
        int[] actual = inst.findAllSteppingNumbers(length);
        System.out.println("output array: " + Arrays.toString(actual));
        assertThat(toObject(actual), is(arrayContainingInAnyOrder(expected)));
    }
}

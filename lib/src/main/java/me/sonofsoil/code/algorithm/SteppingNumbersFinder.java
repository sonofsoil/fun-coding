package me.sonofsoil.code.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given the length N, print all the stepping numbers of length N. Stepping number is any number
 * where the difference between their adjacent digits is exactly 1.
 *
 * <p>eg: 2101 is a stepping number, 1014 is not.
 *
 * <p>Stepping numbers of length 1 = {1,2,3,4,5,6,7,8,9} Steppuing numbers of length 2 =
 * {10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98}
 *
 * <p>Twisted Problem: Given an interval [a,b], print the stepping numbers within that range.
 * Constraints: - a and b are positive integers Extensions: - How will you scale this if the range
 * is [100, 10^10]?
 */
public class SteppingNumbersFinder {
    public int[] findAllSteppingNumbers(int length) {
        if (length <= 0) {
            return new int[0];
        }
        List<Integer> currentList = Arrays.asList(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
        for (int currentLength = 2; currentLength <= length; currentLength++) {
            List<Integer> nextList = new ArrayList<>();
            currentList
                    .stream()
                    .forEach(
                            n -> {
                                int lastDigit = n % 10;
                                if (lastDigit == 0) {
                                    nextList.add(Integer.parseInt(String.format("%d1", n)));
                                } else if (lastDigit == 9) {
                                    nextList.add(Integer.parseInt(String.format("%d8", n)));
                                } else {
                                    nextList.add(Integer.parseInt(String.format("%d%d", n, lastDigit - 1)));
                                    nextList.add(Integer.parseInt(String.format("%d%d", n, lastDigit + 1)));
                                }
                            });
            currentList = nextList;
        }
        return currentList.stream().mapToInt(i -> i).toArray();
    }
}

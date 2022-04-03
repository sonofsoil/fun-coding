package me.sonofsoil.code.algorithm;

import com.google.common.collect.ImmutableList;
import me.sonofsoil.code.ds.ArrayIndexDeque;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given an array of integers, and an integer N, find the length of the longest “N-stable”
 * continuous subarray. An array is defined to be “N-stable” when the pair-wise difference between
 * any two elements in the array is smaller or equal to N.
 *
 * <p>A subarray of a 0-indexed integer array is a contiguous non-empty sequence of elements within
 * an array. For instance, for array [ 4, 2, 3, 6, 2, 2, 3, 2, 7 ], and N = 1 The return value
 * should be 4, since the longest 1-stable subarray is [ 2, 2, 3, 2 ].
 */
public class MaxStableSubarrayFinder {

    public int[] findMaxStableSubarray(int[] inputArray, int maxDistance) {

        // edge cases
        if (inputArray == null || inputArray.length <= 1) {
            return inputArray;
        }

        ImmutableList<Integer> inList = ImmutableList.copyOf(
                Arrays.stream(inputArray).boxed().collect(Collectors.toList()));
        // create minDeque
        final ArrayIndexDeque<Integer> minDeque = ArrayIndexDeque.createMinArrayIndexDeque(inList);
        // create maxDequeue
        final ArrayIndexDeque<Integer> maxDeque = ArrayIndexDeque.createMaxArrayIndexDeque(inList);

        // initialize
        int startIndex = 0; // startIndex pointer
        minDeque.pruneAndPushBackIndex(startIndex); // minDeque init
        maxDeque.pruneAndPushBackIndex(startIndex); // maxDequeue init
        int maxSubArrayLength = 1; // max stable subarray length seen so far
        int maxSubarrayStartIndex = 0; // max stable subarray startIndex pointer seen so far

        // startIndex scanning with current pointer
        for (int currentIndex = 1; currentIndex < inList.size(); currentIndex++) {
            int currentElement = inList.get(currentIndex);

            // check whether subarray breaks due to currentIndex element being min
            if (currentElement < minDeque.getFrontElement()
                    && (maxDeque.getFrontElement() - currentElement) > maxDistance) {

                // remove from maxDequeue head until we find an element where
                // we can startIndex a new stable subarray
                int maxAcceptableValueForStableSubarray = currentElement + maxDistance;
                maxDeque.removeFrontUntilValue(maxAcceptableValueForStableSubarray);

                // startIndex a new stable subarray
                startIndex = maxDeque.isEmpty() ? currentIndex : maxDeque.getFrontIndex();

                // maintain the minDeque head with new startIndex position
                minDeque.removeFrontUntilIndex(startIndex);
            }

            // check whether subarray breaks due to currentIndex element being max
            else if (currentElement > maxDeque.getFrontElement()
                    && (currentElement - minDeque.getFrontElement()) > maxDistance) {

                // remove from minDeque head until we find an element where
                // we can startIndex a new stable subarray
                int minAcceptableValueForStableSubarray = currentElement - maxDistance;
                minDeque.removeFrontUntilValue(minAcceptableValueForStableSubarray);

                // startIndex a new stable subarray
                startIndex = minDeque.isEmpty() ? currentIndex : minDeque.getFrontIndex();

                // maintain the maxDequeue head with new startIndex position
                maxDeque.removeFrontUntilIndex(startIndex);
            }

            // currentIndex element part of subarray seen so far
            else {
                // check whether this is the max subarray seen so far
                int len = currentIndex - startIndex + 1;
                if (len > maxSubArrayLength) {
                    maxSubArrayLength = len;
                    maxSubarrayStartIndex = startIndex;
                }
            }

            // maintain the minDeque tail by removing eliminated max candidates
            minDeque.pruneAndPushBackIndex(currentIndex);

            // maintain the maxDequeue tail by removing eliminated min candidates
            maxDeque.pruneAndPushBackIndex(currentIndex);
        }

        // send result
        return inList
                .subList(maxSubarrayStartIndex, maxSubarrayStartIndex + maxSubArrayLength)
                .stream()
                .mapToInt(i -> i)
                .toArray();
    }
}

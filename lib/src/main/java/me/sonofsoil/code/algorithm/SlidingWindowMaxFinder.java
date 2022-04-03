package me.sonofsoil.code.algorithm;

import com.google.common.collect.ImmutableList;
import me.sonofsoil.code.ds.ArrayIndexDeque;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given an integer array of size N and a window length W, where W <= N, return another integer
 * array which contains the maximum integer of each sliding window while we scan the array from left
 * to right. For example: input array A[] = { 2, 4, 8, 4, 7, 5 } and W = 3 output array B[] = { 8,
 * 8, 8, 7 }
 *
 * <p>B[0] = max { 2, 4, 8 } B[1] = max { 4, 6, 4 } B[2] = max { 8, 4, 7 } B[3] = max { 4, 7, 5 }
 */
public class SlidingWindowMaxFinder {

    public int[] findSlidingWindowMaxValues(int[] input, int window) {

        if (input == null || input.length == 0) {
            return input;
        }

        if (window < 1 && window > input.length) {
            throw new IllegalArgumentException(" window " + window + "must be within valid index range");
        }

        final ImmutableList<Integer> inList = ImmutableList.copyOf(
                Arrays.stream(input).boxed().collect(Collectors.toList()));
        // init maxDequeue
        final ArrayIndexDeque<Integer> maxDequeue = ArrayIndexDeque.createMaxArrayIndexDeque(inList);
        // init window pointer
        int index = 0;

        // Populate maxDequeue for the initial sliding window
        for (; index < window; index++) {
            maxDequeue.pruneAndPushBackIndex(index);
        }

        List<Integer> result = new ArrayList<>();
        // start from the next sliding window
        for (; index < inList.size(); index++) {
            // current front is the max for previous sliding window
            result.add(maxDequeue.getFrontElement());
            // remove the head indeces going out of the current sliding window
            maxDequeue.removeFrontUntilIndex(index - window + 1);
            // add the current index at the tail of current sliding window
            maxDequeue.pruneAndPushBackIndex(index);
        }
        // add the last max element for the last sliding window
        result.add(maxDequeue.getFrontElement());

        // send result
        return result.stream().mapToInt(i -> i).toArray();
    }
}

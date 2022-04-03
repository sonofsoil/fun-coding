package me.sonofsoil.code.ds;

import com.google.common.collect.ImmutableList;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.function.BiFunction;

/**
 * A Deque for maintaining array indeces in order to maintain
 * running min or max within a window(subarray) while performing
 * linear sweep of the input array.
 *
 * @param <T>
 */
public class ArrayIndexDeque<T extends Comparable> {
    private final ImmutableList<T> inputList;
    private final Deque<Integer> deque;
    private final BiFunction<Comparable<T>, T, Boolean> comp;

    private ArrayIndexDeque(ImmutableList<T> inList,
                            BiFunction<Comparable<T>, T, Boolean> comp) {
        this.inputList = inList;
        this.comp = comp;
        this.deque = new ArrayDeque<>();
    }

    /**
     * Creates MinArrayIndexDeque where front of the dequeue holds
     * the index of minimum value seen so far. It also maintains
     * the next candidates in sorted order.
     *
     * @param inList
     * @param <T>
     * @return
     */
    public static <T extends Comparable> ArrayIndexDeque<T>
    createMinArrayIndexDeque(ImmutableList<T> inList) {
        return new ArrayIndexDeque<T>(
                inList,
                new BiFunction<Comparable<T>, T, Boolean>() {
                    @Override
                    public Boolean apply(Comparable<T> first, T second) {
                        return first.compareTo(second) < 0;
                    }
                });
    }

    /**
     * Creates MaxArrayIndexDeque where front of the dequeue holds
     * the index of maximum value seen so far. It also maintains
     * the next candidates in sorted order.
     *
     * @param inList
     * @param <T>
     * @return
     */
    public static <T extends Comparable> ArrayIndexDeque<T>
    createMaxArrayIndexDeque(ImmutableList<T> inList) {
        return new ArrayIndexDeque<T>(
                inList,
                new BiFunction<Comparable<T>, T, Boolean>() {
                    @Override
                    public Boolean apply(Comparable<T> first, T second) {
                        return first.compareTo(second) > 0;
                    }
                });
    }

    /**
     * Checks if empty
     *
     * @return
     */
    public boolean isEmpty() {
        return deque.isEmpty();
    }

    /**
     * Returns the front element dereferencing the front array index
     *
     * @return
     */
    public T getFrontElement() {
        return inputList.get(deque.getFirst());
    }

    /**
     * Returns the front index which points to the minimum
     * value seen so far of the input list for minDeque;
     * points to the maximum value seen so far of the list
     * for maxDeque.
     *
     * @return
     */
    public int getFrontIndex() {
        return deque.getFirst();
    }

    /**
     * Repeatedly removes indeces from the front until the front
     * index is equal or grater than given index.
     *
     * @param index given index
     */
    public void removeFrontUntilIndex(int index) {
        while (!deque.isEmpty() && index > deque.getFirst()) {
            deque.removeFirst();
        }
    }
    /**
     * Repeatedly removes indeces from the front until the front
     * value is equal or grater for MinDeque; equal or smaller
     * for MaxDeque.
     *
     * @param value given value
     */
    public void removeFrontUntilValue(T value) {
        while (!deque.isEmpty()
                && comp.apply(inputList.get(deque.getFirst()), value)) {
            deque.removeFirst();
        }
    }

    /**
     * Prune the tail by repeatedly removing the non-candidate
     * indeces and then add the given index at the end. The value
     * at the tail is non-candidate if it is grater than the value
     * at the supplied index for MinDeque. The value at the tail
     * is non-candidate if it is lower than the value at the
     * supplied index for MaxDeque.
     *
     * @param index input array index to be added at the tail
     */
    public void pruneAndPushBackIndex(int index) {
        if (index < 0 || index >= inputList.size()) {
            throw new ArrayIndexOutOfBoundsException("index " + index + " out of bound");
        }
        while (!deque.isEmpty()
                && comp.apply(inputList.get(index), inputList.get(deque.getLast()))) {
            deque.removeLast();
        }
        deque.addLast(index);
    }
}

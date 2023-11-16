package com.tip.functional;

import com.tip.functional.iterator.InfiniteIterator;
import java.util.Iterator;

public class Preconditions {

    /**
     * @param expression a boolean expression
     * @throws IllegalArgumentException if {@code expression} is false.
     */
    public static void checkArgument(boolean expression) {
        if (!expression)
            throw new IllegalArgumentException();
    }

    /***
     *
     * @param expression a boolean expression
     * @param errorMassage the exception massege to use if the check fails.
     * @throws IllegalArgumentException if {@code expression} is false.
     */
    public static void checkArgument(boolean expression, String errorMassage) {
        if (!expression)
            throw new IllegalArgumentException(String.valueOf(errorMassage));
    }

    /***
     *
     * @param ts an object reference
     * @return the non-null reference that was validated
     * @throws IllegalArgumentException if {@code ts} is null
     */
    public static <T> T checkNotNull(T ts, String error) {
        if (ts == null)
            throw new IllegalArgumentException(error + "is IllegalArguments : null not allowed here");
        return ts;
    }

    public static void checkNotInfiniteIterator(Iterator<?> ts) {
        if (ts instanceof InfiniteIterator<?>)
            throw new IllegalArgumentException("InfiniteIterator");
    }

    public static void checkBothNotInfinitable(Iterator<?> xs, Iterator<?> ys) {
        if (xs instanceof InfiniteIterator<?> && ys instanceof InfiniteIterator<?>) {
            throw new IllegalArgumentException("InfiniteIterator");
        }
    }
}

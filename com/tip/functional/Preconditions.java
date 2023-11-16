package com.tip.functional;

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
    public static <T> T checkNotNull(T ts) {
        if (ts == null)
            throw new IllegalArgumentException("parameter is null");
        return ts;
    }

}

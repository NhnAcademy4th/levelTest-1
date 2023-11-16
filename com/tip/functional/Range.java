package com.tip.functional;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Range implements Iterable<Long> {
    private long startInclusive;
    private long endExclusive;

    /***
     * This Range only iterate Ascending; // Descending isn't permitted.
     * @param startInclusive start value
     * @param endExclusive end value {@code endExclusive} must bigger then {@code startInclusive}.
     */
    public Range(long startInclusive, long endExclusive) {
        this.startInclusive = startInclusive;
        this.endExclusive = endExclusive;
        classInvariant();
    }

    /***
     * default start value is 1.
     * @param endExclusive end value (actual value min value is {@code endExclusive} - 1)
     */
    public Range(long endExclusive) {
        this(1, endExclusive);
    }

    /***
     *
     * @param startInclusive Boundary {Long.minValue ~ LongMaxValue - 1};
     * @param endInclusive Boundary {(startInclusive + 1) ~ LongMaxValue + 1 };
     * @return
     */
    public static Range closed(long startInclusive, long endInclusive) {
        return new Range(startInclusive, endInclusive) {
            @Override
            public long max() {
                return endInclusive;
            }
        };
    }

    private void classInvariant() {
        // start > end
        if (this.startInclusive > this.endExclusive)
            throw new IllegalArgumentException("Range: " + this.startInclusive + " > " + this.endExclusive);

        // start == end -> max() -> will return start -1;
        if (this.startInclusive == this.endExclusive)
            throw new IllegalArgumentException("Range: " + this.startInclusive + " = " + this.endExclusive);
    }

    public long max() {
        return endExclusive - 1;
    }

    public long min() {
        return this.startInclusive;
    }

    public long end() {
        return this.endExclusive;
    }

    /***
     * @return size() only return long Boundary Over value will return maximum of Long Boundary.
     */
    public long size() {
        BigInteger value = BigInteger.valueOf(this.endExclusive - this.startInclusive);

        if (value.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0) return Long.MAX_VALUE;
        else return value.longValue();
    }

    public Iterator<Long> iterator() {
        return new Iterator<Long>() {

            private long current = min();

            public boolean hasNext() {
                return current < end();
            }

            public Long next() {
                if (!hasNext())
                    throw new NoSuchElementException("Range.iterator()");
                long value = current;
                current = Math.addExact(current, 1);
                return value;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Range longs = (Range) o;
        return startInclusive == longs.startInclusive && endExclusive == longs.endExclusive;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startInclusive, endExclusive);
    }
}
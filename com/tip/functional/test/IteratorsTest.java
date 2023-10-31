package com.tip.functional.test;

import static com.tip.Mathx.fibonacci;
import static com.tip.functional.Iterators.*;
import static org.junit.jupiter.api.Assertions.*;

import com.tip.Mathx;
import com.tip.functional.InfiniteIterator;
import com.tip.functional.Iterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.junit.jupiter.api.Test;

public class IteratorsTest {
    @Test
    public void iterateTest() {
        assertTrue(iterate(1, x -> x + 1) instanceof InfiniteIterator);
        assertTrue(!(limit(iterate(1, x -> x + 1), 10) instanceof InfiniteIterator));
        assertTrue(!Iterators.equals(limit(iterate(1, x -> x + 1), 10),
                Stream.iterate(1, x -> x + 1).limit(5).iterator()));
        assertTrue(Iterators.equals(limit(iterate(1, x -> x + 1), 10),
                Stream.iterate(1, x -> x + 1).limit(10).iterator()));
        assertEquals(Iterators.toString(limit(iterate(1, x -> x + 1), 10), " "),
                Stream.iterate(1, x -> x + 1).limit(10).map(String::valueOf)
                        .reduce((x, y) -> x + " " + y).orElse(""));
        assertEquals(Iterators.toString(limit(iterate(1, x -> x + 1), 10), ","),
                Stream.iterate(1, x -> x + 1).limit(10).map(String::valueOf)
                        .collect(Collectors.joining(",")));
        assertThrows(IllegalArgumentException.class, () -> limit(iterate(1, x -> x + 1), -1));
    }

    @Test
    void filterTest() {
        assertTrue(fibonacci() instanceof InfiniteIterator);
        Iterable<Integer> fib = Mathx::fibonacci;
        assertTrue(Iterators.equals(limit(fibonacci(), 10), StreamSupport
                .stream(fib.spliterator(), false).limit(10).iterator()));
        // filter
        assertTrue(Iterators.equals(filter(limit(Iterators.iterate(1, x -> x + 1), 10), x -> x % 2 == 0),
                Stream.iterate(1, x -> x + 1).limit(10).filter(x -> x % 2 == 0).iterator()));
    }
}
// event Driven
// event Driven
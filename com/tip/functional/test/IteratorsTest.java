package com.tip.functional.test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.tip.Mathx;
import com.tip.functional.*;
import static com.tip.functional.Iterators.*;
import static com.tip.Mathx.*;

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
                assertThrows(IllegalArgumentException.class,()->limit(iterate(1, x->x+1),-1));

        }

        @Test
        void filterTest() {
                assertTrue(fibonacci() instanceof InfiniteIterator);
                Iterable<Integer> fib = Mathx::fibonacci;
                assertTrue(Iterators.equals(limit(fibonacci(), 10), StreamSupport
                        .stream(fib.spliterator(), false).limit(10).iterator()));
                // filter
                assertTrue(Iterators.equals(filter(limit(Iterators.iterate(1,x->x+1),10),x -> x%2==0),
                        Stream.iterate(1,x->x+1).limit(10).filter(x->x%2==0).iterator()));
        }

        public static void main(String[] args) {
//                Iterator<Integer> a = Arrays.asList(1,2,3,4).iterator();
//                Iterator<Integer> b = Arrays.asList(1,2,3,4).iterator();
//                System.out.println(Iterators.equals(a,b));
//
//                Iterator<Integer> aa = Arrays.asList(1,2,3,4).iterator();
//                System.out.println(Iterators.count(aa));
//
//                limit(Iterators.generate(Math::random),10).forEachRemaining((x)->System.out.print(x+", "));
//
//                filter(limit(Iterators.iterate(1,x -> x + 1),10),(x) -> x % 2 == 0);
//                filter(Arrays.asList(1,2,3,4,5,6,7,8,9,10)
//                        .iterator(),x->x%2==0)
//                        .forEachRemaining(System.out::print);
                filter(limit(Iterators.iterate(1,(x)->x+1),10),x -> x % 2 == 0).forEachRemaining(System.out::print);
                System.out.println();
                Stream.iterate(1,x->x+1).limit(10).filter(x -> x % 2 == 0).forEach(System.out::print);
//                Iterator<Integer> aaa = Arrays.asList(1,2,3,4).iterator();
//                System.out.println(aaa.next());
//                System.out.println(aaa.next());
//                System.out.println(aaa.next());
//                System.out.println(aaa.next());
//                System.out.println(aaa.next());
        }
}
// event Driven
// event Driven
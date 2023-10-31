package com.tip.functional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Iterators {

    public static <E, R> R reduce(Iterable<E> es, BiFunction<R, E, R> biFunction, R init) {
        R result = init;
        for (E e : es) {
            result = biFunction.apply(result, e);
        }
        return result;
    }

    public static <E, R> R reduce(Iterator<E> es, BiFunction<R, E, R> biFunction, R init) {
        return reduce(() -> es, biFunction, init);
    }

    public static <T> boolean equals(Iterator<T> xs, Iterator<T> ys) { // TODO: reduce,zip를 써서
        return reduce(zip(Object::equals, xs, ys), (x, y) -> x && y, true) && (xs.hasNext() == ys.hasNext());
    }

//    public static <T> String toString(Iterator<T> es, String separator) { // TODO: redude를 써서
//        String str = reduce(es, (prev, next) -> prev + separator + next, "");
//        return str.replaceFirst(separator,"");
//    }

    public static <T> String toString(Iterator<T> es, String separator) { // TODO: redude를 써서
        String firstValue = "";
        if (es.hasNext()) {
            firstValue = es.next().toString();
        }
        return reduce(es, (prev, next) -> prev + separator + next, firstValue);
    }


    public static <E, R> Iterator<R> map(Iterator<E> es, Function<E, R> function) {
        return new Iterator<R>() {
            public boolean hasNext() {
                return es.hasNext();
            }

            public R next() {
                return function.apply(es.next());
            }
        };
    }

    public static <E> Iterator<E> filter(Iterator<E> iterator, Predicate<E> predicate) {
        // TODO: Bug를 찾을 수 있는 test code를 IteratorTest.filterTest에 쓰고, Bug 고치기
        // findFirst를 써서 풀기
        return new Iterator<E>() {
            private E current;

            public boolean hasNext() {
                if (current == null) {
                    current = findFirst(iterator, predicate);
                }
                return current != null;
            }

            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("filter");
                }
                E old = current;
                current = findFirst(iterator, predicate);
                return old;
            }
        };
    }

    public static <E> E findFirst(Iterator<E> iterator, Predicate<E> predicate) {
        while (iterator.hasNext()) {
            E first = iterator.next();
            if (predicate.test(first)) {
                return first;
            }
        }
        return null;
    }

    public static <T> InfiniteIterator<T> iterate(T seed, UnaryOperator<T> f) {
        return new InfiniteIterator<T>() {
            public T current = seed;

            @Override
            public T next() {
                T old = current;
                current = f.apply(current);
                return old;
            }
        };
    }

    public static <T> Iterator<T> limit(Iterator<T> iterator, long maxSize) { // TODO
        return new Iterator<>() {
            private long count = 0;

            @Override
            public boolean hasNext() {
                return iterator.hasNext() && (count < maxSize);
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("limit");
                }
                count = Math.addExact(count, 1);
                return iterator.next();
            }
        };
    }

    public static <T> InfiniteIterator<T> generate(Supplier<T> supplier) { // TODO:
        return supplier::get;
    }

    public static <X, Y, Z> Iterator<Z> zip(BiFunction<X, Y, Z> biFunction, Iterator<X> xIterator,
                                            Iterator<Y> yIterator) {
        return new Iterator<Z>() {
            public boolean hasNext() {
                return xIterator.hasNext() && yIterator.hasNext();
            }

            public Z next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("zip");
                }
                return biFunction.apply(xIterator.next(), yIterator.next());
            }
        };
    }

    public static <E> long count(Iterator<E> iterator) {
        // TODO: reduce를 써서
        return reduce(iterator, (sum, value) -> sum + 1, 0);
    }

    public static <T> T get(Iterator<T> iterator, long index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index < " + index);
        }
        return getLast(limit(iterator, index + 1));
    }

    private static <T> T getLast(Iterator<T> iterator) {
        while (true) {
            T current = iterator.next();
            if (!iterator.hasNext()) {
                return current;
            }
        }
    }

    public static <T> List<T> toList(Iterator<T> iterator) {
        List<T> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    public static <E> void print(Iterator<E> iterator, String separator,
                                 java.io.PrintStream printStream) {
        printStream.print(toString(iterator, separator));
    }

    public static <E> void print(Iterator<E> iterator, String separator) {
        print(iterator, separator, System.out);
    }

    public static <E> void println(Iterator<E> iterator, String separator,
                                   java.io.PrintStream printStream) {
        print(iterator, separator, printStream);
        printStream.println();
    }

    public static <E> void println(Iterator<E> iterator, String separator) {
        println(iterator, separator, System.out);
    }

    public static <E> void println(Iterator<E> iterator) {
        println(iterator, ", ");
    }

    private Iterators() {
    }

}



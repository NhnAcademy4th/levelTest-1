package com.tip.functional.iterator;

import static com.tip.functional.Preconditions.*;

import java.util.*;
import java.util.function.*;

public class Iterators {

    /***
     * @apiNote  {@code es} Override hasNext() return always true it cause infinite roop.
     * @param es Iterable value to reducing. / Ignore null value in Iterable<E>
     * @param biFunction Function that how to reduce.
     * @param init start value (Type R)
     * @return Return Type R value from Iterable es, processing by BiFunction ,start with init
     * @throws IllegalArgumentException if {@code biFunction},{@code init} is null or {@code es} instanceof Infinitable
     */
    public static <E, R> R reduce(Iterable<E> es, BiFunction<R, E, R> biFunction, R init) {
        checkNotNull(init, "init");
        checkNotNull(biFunction, "");

        R result = init;
        for (E e : es) {
            result = biFunction.apply(result, e);
        }
        return result;
    }

    /***
     * @param es Iterable value to reducing.
     * @param biFunction Function that how to reduce.
     * @param init start value (Type R)
     * @return Return Type R value from Iterable es, processing by BiFunction ,start with init
     * @throws IllegalArgumentException if {@code biFunction}, {@code init} is null
     * @throws IndexOutOfBoundsException if{@code es} hasNext return always true
     */
    public static <E, R> R reduce(Iterator<E> es, BiFunction<R, E, R> biFunction, R init) {
        checkNotInfiniteIterator(es);
        return reduce(() -> es, biFunction, init);
    }

    /***
     * @param xs Iterator<T>
     * @param ys Iterator<T>
     * @return Return {@code true} if {xs ,ys} iterator equals until xs.hasNext() == xs.hasNext();
     * @throws IllegalArgumentException if {@code xs}, {@code ys} is null
     */
    public static <T> boolean equals(Iterator<T> xs, Iterator<T> ys) {
        // TODO: reduce, zip을 써서 null, 중복 test, infinite test
        checkNotNull(xs, "First Param");
        checkNotNull(ys, "Second Param");
        checkBothNotInfinitable(xs, ys);

        if (xs.getClass() != ys.getClass()) return false;

        if (xs == ys) {
            while (xs.hasNext()) {
                xs.next();
            }
            return true;
        }

        return reduce(zip(Objects::equals, xs, ys), (x, y) -> x && y, true) && xs.hasNext() == ys.hasNext();
    }


    /***
     *@apiNote The iterator will be left exhausted: its{@code hasNext} method will Return false,
     * Iterator can contain null value
     * @return a String representation of{@code iterator}, with format{@code [e1,e2,e3, ..., en] }/
     * @throws IllegalArgumentException if {@code es} or {@code separator} is null
     */
    public static <T> String toString(Iterator<T> es, String separator) {
        // TODO: reduce를 써서
        checkNotNull(es, "fisrt param");
        checkNotNull(separator, "separator");

        StringBuilder init = new StringBuilder();
        if (es.hasNext()) {
            init.append(es.next());
        }
        reduce(es, (acc, element) -> acc.append(separator).append(element), init);
        return init.toString();
    }

    /***
     * @param es Iterator<E>
     * @param function Function E -> R
     * @return Iterator<R> new Iterator mapping by {@code function}
     */
    public static <E, R> Iterator<R> map(Iterator<E> es, Function<E, R> function) {
        checkNotNull(es, "first param");
        checkNotNull(function, "function");
        return new Iterator<R>() {
            public boolean hasNext() {
                return es.hasNext();
            }

            public R next() {
                return function.apply(es.next());
            }
        };
    }

    public static <E, R> InfiniteIterator<R> map(InfiniteIterator<E> es, Function<E, R> function) {
        checkNotNull(es, "firstParam");
        checkNotNull(function, "secondParam");
        return new InfiniteIterator<R>() {
            public boolean hasNext() {
                return es.hasNext();
            }

            public R next() {
                return function.apply(es.next());
            }
        };
    }

    /**
     * @return Iterator<E> that contains all elements satisfy the input predicate.
     * @throws IllegalArgumentException if {@code iterator}, {@code predicate} is null
     * @apiNote has no next element. next() return null value. && hasNext() return false
     */
    public static <E> Iterator<E> filter(Iterator<E> iterator, Predicate<E> predicate) {
        // TODO: Bug를 찾을 수 있는 test code를 IteratorTest.filterTest에 쓰고, Bug 고치기
        checkNotNull(iterator, "fisrtParam");
        checkNotNull(predicate, "second param");
        return new Iterator<E>() {
            private E current;

            public boolean hasNext() {
                if (current == null)
                    current = findFirst(iterator, predicate);
                if (current == null)
                    return false;
                current = findFirst(iterator, predicate);
                return true;
            }

            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return current;
            }
        };
    }

    public static <E> InfiniteIterator<E> filter(InfiniteIterator<E> iterator, Predicate<E> predicate) {
        // TODO: Bug를 찾을 수 있는 test code를 IteratorTest.filterTest에 쓰고, Bug 고치기
        checkNotNull(iterator, "firstParam");
        checkNotNull(predicate, "secondParam");
        return new InfiniteIterator<E>() {
            private E current = findFirst(iterator, predicate);

            public boolean hasNext() {
                return Objects.nonNull(current);
            }

            public E next() {
                E tmp = current;
                current = findFirst(iterator, predicate);
                return tmp;
            }
        };
    }

    /***
     * @apiNote will nexted until find first match Value // or return null
     * @return Findfirst {@code predicate} match Value in {@code iterator}
     * @throws NoSuchElementException find nothing match {@code predicate}
     */
    public static <E> E findFirst(Iterator<E> iterator, Predicate<E> predicate) {
        checkNotNull(iterator, "firstParam");
        checkNotNull(predicate, "secondParam");
        while (iterator.hasNext()) {
            E first = iterator.next();
            if (predicate.test(first)) {
                return first;
            }
        }
        return null;
    }

    /***
     *
     * @param seed first <T> type Value to iterate
     * @param f Functional Interface T -> T
     * @return new Iterater that has no limit.
     * @apiNote : default method hasNext() returns always true;
     */
    public static <T> InfiniteIterator<T> iterate(T seed, UnaryOperator<T> f) {
        checkNotNull(seed, "firstParam");
        checkNotNull(f, "secondParam");
        return new InfiniteIterator<T>() {
            T current = seed;

            @Override
            public T next() {
                T old = current;
                current = f.apply(current);
                return old;
            }
        };
    }

    /***
     *
     * @param iterator the iterator to limit
     * @param maxSize limiteSize the maximum number of elements in the returned iterator
     * @return An Iterator iterate until {@code maxSize}
     * @throws IllegalArgumentException if {@code maxSize} is negative.
     * @throws NoSuchElementException if {@code iterater} is null.
     */
    public static <T> Iterator<T> limit(Iterator<T> iterator, long maxSize) {
        // TODO
        checkNotNull(iterator, "firstParam");
        checkArgument(maxSize >= 0, "limit is negative");

        return new Iterator<T>() {
            private long count = 0;

            @Override
            public boolean hasNext() {
                if (!iterator.hasNext() && (count < maxSize))
                    throw new IllegalArgumentException("iterator not enough to get" + maxSize);
                return iterator.hasNext() && (count < maxSize);
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw (count < maxSize) ? new IllegalArgumentException("iterator not enough to get" + maxSize) :
                            new NoSuchElementException();
                count = Math.addExact(count, 1);
                return iterator.next();
            }
        };
    }


    /***
     * @return Infinite value calculated by supplier.get().
     */
    public static <T> InfiniteIterator<T> generate(Supplier<T> supplier) {
        checkNotNull(supplier, "firstParam");
        return new InfiniteIterator<T>() {
            @Override
            public T next() {
                return supplier.get();
            }
        };
    }

    /***
     * @return an Iterator in which each element is the result of passing the corresponding element of each of {@code IteraterX}
     */
    public static <X, Y, Z> Iterator<Z> zip(BiFunction<X, Y, Z> biFunction, Iterator<X> xIterator,
                                            Iterator<Y> yIterator) {
        checkNotNull(biFunction, "firstParam");
        checkNotNull(xIterator, "secondParam");
        checkNotNull(yIterator, "thirdParam");
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

    public static <X, Y, Z> InfiniteIterator<Z> zip(BiFunction<X, Y, Z> biFunction, InfiniteIterator<X> xIterator,
                                                    Iterator<Y> yIterator) {
        checkNotNull(biFunction, "firstParam");
        checkNotNull(xIterator, "secondParam");
        checkNotNull(yIterator, "thirdparam");
        return new InfiniteIterator<Z>() {
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

    /***
     *
     * @return Long value that count Iterator until hasNext() -> true
     * @apiNote after this function iterater.hasNext() will return false.
     */
    public static <E> long count(Iterator<E> iterator) {
        checkNotNull(iterator, "firstParam");
        checkNotInfiniteIterator(iterator);
        // TODO: reduce를 써서
        long count = 0;
        try {
            count = reduce(iterator, (init, ignore) -> Math.addExact(init, 1), count);
        } catch (ArithmeticException e) {
            return Long.MAX_VALUE;
        }
        return count;
    }

    /***
     * @return  {@code index} position value from iterater
     * @apiNote iterator will be nexted until {@code index}
     * @throws IndexOutOfBoundsException if index is negative
     */
    public static <T> T get(Iterator<T> iterator, long index) {
        checkNotNull(iterator, "firstParam");
        if (index < 0) {
            throw new IllegalArgumentException("index < " + index);
        }
        return getLast(limit(iterator, index + 1));
    }

    /***
     * @return {@code iterator}'s lastValue
     * @apiNote Iterator will be done.
     */
    public static <T> T getLast(Iterator<T> iterator) {
        checkNotInfiniteIterator(iterator);
        checkNotNull(iterator, "firstParam");
        while (true) {
            T current = iterator.next();
            if (!iterator.hasNext()) {
                return current;
            }
        }
    }

    /***
     * @return transpose iterator to list
     */
    public static <T> List<T> toList(Iterator<T> iterator) {
        checkNotNull(iterator, "firstParam");
        List<T> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    /***
     * @apiNote print toString result
     */
    public static <E> void print(Iterator<E> iterator, String separator,
                                 java.io.PrintStream printStream) {
        printStream.print(toString(iterator, separator));
    }

    /***
     * @apiNote print toString result
     */
    public static <E> void print(Iterator<E> iterator, String separator) {
        print(iterator, separator, System.out);
    }

    /***
     * @apiNote print toString result
     */
    public static <E> void println(Iterator<E> iterator, String separator,
                                   java.io.PrintStream printStream) {
        print(iterator, separator, printStream);
        printStream.println();
    }

    /***
     * @apiNote print toString result
     */
    public static <E> void println(Iterator<E> iterator, String separator) {
        println(iterator, separator, System.out);
    }

    /***
     * @apiNote print toString result
     */
    public static <E> void println(Iterator<E> iterator) {
        println(iterator, ", ");
    }

    private Iterators() {
    }
}



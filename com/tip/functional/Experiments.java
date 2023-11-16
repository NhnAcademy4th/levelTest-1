package com.tip.functional;

import java.util.NoSuchElementException;

public class Experiments<T extends Number> implements InfiniteIterator<T> {
    private InfiniteIterator<T> iterator;
    private String effect;
    private String distribution;
    private long count;
    private double sum;

    public Experiments(InfiniteIterator<T> iterator, String effect, String distribution) {
        this.iterator = iterator;
        this.effect = effect;
        this.distribution = distribution;
    }

    public void report() {
        System.out.println(sum / count);
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext())
            throw new NoSuchElementException("experiment next");

        count = Math.addExact(count, 1);
        T element = iterator.next();
        sum += element.doubleValue();

        return element;
    }
}
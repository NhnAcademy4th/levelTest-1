package com.tip.functional;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Experiments<T extends Number> implements Iterator<T> {
    // TODO: 채우기
    Iterator<T> iterator;
    private long count;
    private double sum;
    private final String group;
    private final String title;

    public Experiments(Iterator<T> iterator, String group, String title) {
        this.iterator = iterator;
        this.group = group;
        this.title = title;
    }

    public void report() {
        System.out.println(title + " " + group);
        System.out.println(sum / count);
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException(this.getClass().getSimpleName());
        }
        count = Math.addExact(count, 1);
        sum += iterator.next().doubleValue();
        return iterator.next();
    }
}

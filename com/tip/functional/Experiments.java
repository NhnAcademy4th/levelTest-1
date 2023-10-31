package com.tip.functional;

import static com.tip.functional.Iterators.limit;
import static com.tip.functional.Iterators.reduce;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Experiments<T extends Number> implements Iterator<T> {

    Iterator<T> iterator;
    String effect;
    String distribution;
    private final int MAX_ITERATION = 7000;
    int count;
    public Experiments(Iterator<T> iterator, String effect, String distribution) {
        this.count = 0;
        this.iterator = iterator;
        this.effect = effect;
        this.distribution = distribution;
    }

    public void report(){
        double count = reduce(limit(iterator,MAX_ITERATION),(init, x)-> init + x.intValue(),0);
        System.out.println(count / MAX_ITERATION);
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public T next() {
        if(!hasNext())
            throw new NoSuchElementException("experiment next");
        count++;
        return iterator.next();
    }
    // TODO: 채우기
}


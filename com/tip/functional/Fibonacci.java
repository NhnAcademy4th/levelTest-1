package com.tip.functional;

public class Fibonacci implements InfiniteIterator<Integer> {
    // TODO: 채우기

    private int prev;
    private int current;


    public Fibonacci() {
        this.prev = 0;
        this.current = 1;
    }

    @Override
    public Integer next() {
        int temp = prev;
        prev = current;
        current = temp + current;
        return prev;
    }

}

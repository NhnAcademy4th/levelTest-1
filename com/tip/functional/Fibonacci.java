package com.tip.functional;

public class Fibonacci implements InfiniteIterator<Integer> {

    private int n;
    private int prev;
    private int cur;
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Integer next() {
        int temp = prev;
        prev = cur;
        return null;
    }
    // TODO: 채우기
}

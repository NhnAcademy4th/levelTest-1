package com.tip.functional;

public class Fibonacci implements InfiniteIterator<Integer> {

    private int prev;
    private int cur;

    public Fibonacci() {
        prev = 0;
        cur = 1;
    }

    @Override
    public Integer next() {
        int temp = cur + prev;
        prev = cur;
        cur = temp;
        return cur;
    }

    // TODO: 채우기
}

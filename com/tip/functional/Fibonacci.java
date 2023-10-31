package com.tip.functional;

public class Fibonacci implements InfiniteIterator<Integer> {

    // TODO: 채우기
    private int prev;
    private int current;

    private int next;

    @Override
    public Integer next() {
        if (current == 0) {
            prev = 1;
            current = 1;
            return current;
        }
        int tmp = prev;
        prev = current;
        current = tmp + current;
        return prev;
    }

}

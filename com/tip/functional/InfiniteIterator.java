package com.tip.functional;

public interface InfiniteIterator<T> {
    // TODO: 채우기
    default boolean hasNext() {
        return true;
    }

    T next();
}
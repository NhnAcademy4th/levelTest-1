package com.tip.functional.iterator;

import java.util.Iterator;

public interface InfiniteIterator<T> extends Iterator<T> {
    // TODO: 채우기
    default boolean hasNext() {
        return true;
    }

    T next();
}
package com.tip.functional.iterator;

import java.util.Iterator;

public interface CustomIterator<T> extends Iterator<T> {
    boolean hasNext();

    T next();
}
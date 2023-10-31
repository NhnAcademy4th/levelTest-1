package com.tip.functional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Fibonacci implements InfiniteIterator<Integer> {
    // TODO: 채우기
    Iterator<Integer> fibo;

    public Fibonacci(int number){
        List<Integer> fibo = new ArrayList();
        for (int i = 0; i < number + 1; i++) {
            fibo.add(calc(i));
        }
        this.fibo = fibo.stream().iterator();
    }

    public Fibonacci(){
        this.fibo = Iterators.iterate(0,this::calc);
    }

    public int calc(int number){
        switch(number){
            case 0 :
                return 0;
            case 1 :
            case 2 :
                return 1;
            default:
                return calc(number - 2) + calc(number - 1);
        }
    }

    @Override
    public boolean hasNext() {
        return fibo.hasNext();
    }

    @Override
    public Integer next() {
        if(!hasNext()) throw new IllegalArgumentException("fibo : next");
        return fibo.next();
    }

}

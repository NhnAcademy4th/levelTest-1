package com.tip.functional.test;

import java.util.Iterator;

import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.tip.functional.Range;

public class RangeTest {
    @Test
    public void rangeTest() {

        // TODO: 모든 기능을 고르게 테스트 할 수 있는 코드 적어보기
        assertEquals(new Range(1, 10).max(),9);
        assertEquals(new Range(1,10).min(), 1);
        assertEquals(new Range(1,10).end(),10);
        assertEquals(new Range(1,10).size(),9);
        assertEquals(new Range(1,10),new Range(10));
        assertEquals(new Range(1,11), Range.closed(1,10));

        assertThrows(IllegalArgumentException.class, () -> new Range(100,10));
        assertThrows(IllegalArgumentException.class, () ->new Range(1).iterator().hasNext());
        assertThrows(IllegalArgumentException.class,() -> new Range(0).iterator().hasNext());
        assertThrows(IllegalArgumentException.class,() -> new Range(-1).iterator().hasNext());
        assertThrows(ArithmeticException.class,() -> new Range(0,Long.MAX_VALUE + 1).iterator().hasNext());
        assertThrows(ArithmeticException.class, () -> new Range(Long.MIN_VALUE-1,100).iterator().hasNext()); // 11번째줄 안하면 IllegalArgumentException 던짐, 맞는건지?

        Iterator test = new Range(0,10).iterator();

        long i = 0;

        while(test.hasNext()){
            assertEquals(test.next(),i++);
        }
    }
}

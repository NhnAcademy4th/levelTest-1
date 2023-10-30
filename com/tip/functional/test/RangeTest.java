package com.tip.functional.test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.tip.functional.Range;
import static com.tip.Mathx.*;

public class RangeTest {
    @Test
    public void rangeTest() {

        // TODO: 모든 기능을 고르게 테스트 할 수 있는 코드 적어보기
        assertEquals(new Range(0, 10).max(),9);
        assertEquals(new Range(0,10).min(), 0);
        assertEquals(new Range(0,10).end(),10);
        assertEquals(new Range(0,10).size(),10);
        assertEquals(new Range(0,10),new Range(10));

        assertThrows(IllegalArgumentException.class, () ->{new Range(100,10);});

        assertTrue(new Range(1).iterator().hasNext());
        assertThrows(IllegalArgumentException.class,() -> new Range(0).iterator().hasNext());

        Iterator test = new Range(0,10).iterator();

        long i = 0;

        while(test.hasNext()){
            assertEquals(test.next(),i++);
        }
    }
}

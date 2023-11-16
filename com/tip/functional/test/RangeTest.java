package com.tip.functional.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.tip.functional.Range;
import java.util.Iterator;
import org.junit.jupiter.api.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RangeTest {

    @Nested
    @DisplayName("Constructor / two params")
    class constructorTwoParams {
        Range zero2One;
        Range minusOne2Zero;
        Range min2Max;

        @DisplayName("Constructor PreCondition check / two params")
        @BeforeEach
        void constructorTwoParamsTest() {
            zero2One = new Range(0, 1);
            minusOne2Zero = new Range(-1, 0);
            min2Max = new Range(Long.MIN_VALUE, Long.MAX_VALUE);
        }

        @DisplayName("Two params / max Test")
        @Order(1)
        @Test
        void maxTest() {
            assertEquals(0L, zero2One.max());
            assertEquals(-1L, minusOne2Zero.max());
            assertEquals(Long.MAX_VALUE - 1, min2Max.max());
        }

        @DisplayName("Two params / min Test")
        @Order(1)
        @Test
        void minTest() {
            assertEquals(0L, zero2One.min());
            assertEquals(-1L, minusOne2Zero.min());
            assertEquals(Long.MIN_VALUE, min2Max.min());
        }

        @DisplayName("Two params / end Test")
        @Order(1)
        @Test
        void endTest() {
            assertEquals(1L, zero2One.end());
            assertEquals(0L, minusOne2Zero.end());
            assertEquals(Long.MAX_VALUE, min2Max.end());
        }
    }

    @DisplayName("Constructor PreCondition check 1/ one params (default start with 1)")
    @Order(1)
    @Test
    void constructorOneParamsTest() {
        new Range(2);
        new Range(3);
        new Range(Long.MAX_VALUE);
    }

    @DisplayName("Constructor PreCondition check 2/ closed method")
    @Order(1)
    @Test
    void constructorClosedTest() {
        new Range(0, 1);
        new Range(-1, 0);
        new Range(Long.MIN_VALUE, Long.MAX_VALUE);
    }

    @DisplayName("Constructor PreCondition check / two params Exceptions")
    @Order(1)
    @Test
    void constructorTest1Exceptions() {
        assertThrows(IllegalArgumentException.class, () -> new Range(0, 0));
        assertThrows(IllegalArgumentException.class, () -> new Range(Long.MIN_VALUE - 1, 0));
        assertThrows(IllegalArgumentException.class, () -> new Range(0, Long.MAX_VALUE + 1));
    }

    @DisplayName("Constructor PreCondition check 1/ one params (default start with 1) Exceptions")
    @Order(1)
    @Test
    void constructorTest2Exceptions() {
        assertThrows(IllegalArgumentException.class, () -> new Range(0));
        assertThrows(IllegalArgumentException.class, () -> new Range(1));
        assertThrows(IllegalArgumentException.class, () -> new Range(Long.MAX_VALUE + 1));
    }

    @DisplayName("Constructor PreCondition check 2/ closed method Exceptions")
    @Order(1)
    @Test
    void constructorClosedTestExceptions() {
        assertThrows(IllegalArgumentException.class, () -> Range.closed(0, 0));
        assertThrows(IllegalArgumentException.class, () -> Range.closed(Long.MIN_VALUE - 1, 0));
        assertThrows(IllegalArgumentException.class, () -> new Range(0, Long.MAX_VALUE + 1));
    }

    @DisplayName("")

    @Test
    public void rangeTest() {
        // TODO: 모든 기능을 고르게 테스트 할 수 있는 코드 적어보기
        assertEquals(new Range(1, 10).max(), 9);
        assertEquals(new Range(1, 10).min(), 1);
        assertEquals(new Range(1, 10).end(), 10);
        assertEquals(new Range(1, 10).size(), 9);
        assertEquals(new Range(1, 10), new Range(10));
        assertEquals(new Range(1, 11), Range.closed(1, 10));

        assertThrows(IllegalArgumentException.class, () -> new Range(100, 10));
        assertThrows(IllegalArgumentException.class, () -> new Range(1).iterator().hasNext());
        assertThrows(IllegalArgumentException.class, () -> new Range(0).iterator().hasNext());
        assertThrows(IllegalArgumentException.class, () -> new Range(-1).iterator().hasNext());

        assertThrows(ArithmeticException.class, () -> new Range(0, Long.MAX_VALUE + 1).iterator().hasNext());
        assertThrows(IllegalArgumentException.class, () -> new Range(Long.MIN_VALUE - 1, 100).iterator().hasNext());

        Iterator test = new Range(0, 10).iterator();

        long i = 0;

        while (test.hasNext()) {
            assertEquals(test.next(), i++);
        }
    }
}

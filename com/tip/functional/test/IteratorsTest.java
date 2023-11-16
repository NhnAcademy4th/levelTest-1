package com.tip.functional.test;

import static com.tip.functional.Iterators.*;
import static org.junit.jupiter.api.Assertions.*;

import com.tip.functional.InfiniteIterator;
import com.tip.functional.Iterators;
import java.util.*;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IteratorsTest {
    final long INFINITE_MAX_TEST = 10_000_000;
    Iterator<Integer> intIterator1;
    Iterator<Integer> intIterator2;
    Iterator<Double> doubleIterator1;
    Iterator<Double> doubleIterator2;
    InfiniteIterator<Integer> infiniteIntIterator1;
    InfiniteIterator<Integer> infiniteIntIterator2;
    InfiniteIterator<Double> infiniteDoubleIterator1;
    InfiniteIterator<Double> infiniteDoubleIterator2;

    @DisplayName("Reduce params Null check")
    @Test
    void reduceNullTest() {
        assertThrows(IllegalArgumentException.class, () -> reduce(intIterator1, null, 0));
        assertThrows(IllegalArgumentException.class, () -> reduce(intIterator1, (x, y) -> (x), null));
        assertThrows(IllegalArgumentException.class, () -> reduce(intIterator1, null, null));
    }

    @DisplayName("Reduce postCondition / Integer")
    @Test
    void reduceIteratorPsotCondition1() {
        assertEquals(55, reduce(intIterator1, Integer::sum, 0));
    }

    @DisplayName("Reduce postCondition / Double")
    @Test
    void reduceIteratorPsotCondition2() {
        assertEquals(55d, reduce(doubleIterator1, Double::sum, 0d));
    }

    @DisplayName("Reduce postConditon 1")
    @Test
    void reduceIteratorPostCondition() {
        Iterable<Integer> testIterable = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Iterator<Integer> testIterator = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).iterator();
        assertEquals(55, reduce(testIterable, Integer::sum, 0));
        assertEquals(55, reduce(testIterator, Integer::sum, 0));
    }

    @DisplayName("Reduce PostCondition 2 / after iteration hasNext must be false;")
    @Test
    void reduceIteratorPostCondtion2() {
        reduce(intIterator1, (x, y) -> 0, 0);
        assertFalse(intIterator1.hasNext());
    }

    @DisplayName("Reduce PostCondition 3 / after iteration next() return null")
    @Test
    void reduceIteratorPostCondtion3() {
        reduce(intIterator1, (x, y) -> 0, 0);
        assertThrows(NoSuchElementException.class, () -> intIterator1.next());
    }

    @DisplayName("Equals null check")
    @Test
    void equalsNullTest() {
        Iterator<Objects> testIterator1 = null;
        Iterator<Objects> testIterator2 = null;
        assertThrows(IllegalArgumentException.class, () -> Iterators.equals(testIterator1, testIterator2));
    }

    @DisplayName("Equals PreCondition Test / Iterator include null")
    @Test
    void eqaulsPostconditionTest3() {
        Iterator<Integer> testIterator1 = Arrays.asList(1, 2, 3, 4, null, 5).iterator();
        Iterator<Integer> testIterator2 = Arrays.asList(1, 2, 3, 4, null, 5).iterator();
        assertThrows(NullPointerException.class, () -> Iterators.equals(testIterator1, testIterator2));
    }

    @DisplayName("Equals PostCondition Test1 / Equal length")
    @Test
    void eqaulsPostconditionTest1() {
        assertTrue(Iterators.equals(intIterator1, intIterator2));
    }

    @DisplayName("Equals PostCondition Test2 / not Equal length")
    @Test
    void eqaulsPostconditionTest2() {
        Iterator<Integer> testIterator1 = Arrays.asList(1, 2, 3, 4).iterator();
        Iterator<Integer> testIterator2 = Arrays.asList(1, 2, 3, 4, 5).iterator();
        assertFalse(Iterators.equals(testIterator1, testIterator2));
    }

    @DisplayName("Equals PostCondition Test3 / same length, different value")
    @Test
    void equalsPostConditionTest3() {
        Iterator<Integer> testIterator1 = Arrays.asList(1, 2, 3, 4).iterator();
        Iterator<Integer> testIterator2 = Arrays.asList(4, 3, 2, 1).iterator();
        assertFalse(Iterators.equals(testIterator1, testIterator2));
    }

    @DisplayName("Equals PostCondition Test4 / Compare two Integer InfiniteIterator")
    @Order(2)
    @Test
    void equalsPostConditionTest4() {
        assertTrue(Iterators.equals(infiniteIntIterator1, infiniteIntIterator2));
    }

    @DisplayName("Equals PostCondition Test5 / Compare two Double InfiniteIterator")
    @Order(2)
    @Test
    void equalsPostConditionTest5() {
        assertTrue(Iterators.equals(infiniteDoubleIterator1, infiniteDoubleIterator2));
    }

    @DisplayName("Equals PostCondition Test6 / Compare One Integer Iterator")
    @Test
    void equalsPostCondtionTest6() {
        assertTrue(Iterators.equals(intIterator1, intIterator1));
    }

    @DisplayName("Equals PostCondition Test7 / Compare One Double Iterator")
    @Test
    void equalsPostCondtionTest7() {
        assertTrue(Iterators.equals(doubleIterator1, doubleIterator2));
    }

    @DisplayName("Equals PostCondition Test8 / Compare One Integer InfiniteIterator")
    @Order(2)
    @Test
    void equalsPostCondtionTest8() {
        assertTrue(Iterators.equals(infiniteIntIterator1, infiniteIntIterator2));
    }

    @DisplayName("Equals PostCondition Test9 / Compare One Integer InfiniteIterator")
    @Order(2)
    @Test
    void equalsPostCondtionTest9() {
        assertTrue(Iterators.equals(infiniteIntIterator1, infiniteIntIterator2));
    }

    @DisplayName("Equals PostCondition Test10 / Compare One Double InfiniteIterator")
    @Order(2)
    @Test
    void equalsPostCondtionTest10() {
        assertTrue(Iterators.equals(infiniteDoubleIterator1, infiniteDoubleIterator2));
    }

    @DisplayName("toString() PreCondition Test1 / null check 1")
    @Test
    void toStringNullCheck1() {
        Iterator<Object> nullIterator = Arrays.asList(null, null, null).iterator();
        assertThrows(IllegalArgumentException.class, () -> Iterators.toString(nullIterator, ","));
    }

    @DisplayName("toString() PreCondition Test2 / null check 2")
    @Test
    void toStringNullCheck2() {
        Iterator<Integer> nullSeparator = Arrays.asList(1, 2, 3, 4, 5).iterator();
        assertThrows(IllegalArgumentException.class, () -> Iterators.toString(nullSeparator, null));
    }

    @DisplayName("toString() PostCondition Test3 / toString")
    @Test
    void toStringNullCheck3() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.toString(null, ","));
    }

    @DisplayName("toString() PostCondition Test4 / toString")
    @Test
    void toStringTest() {
        Iterator<Integer> iterator = Arrays.asList(1, 2, 3, 4, 5).iterator();
        assertEquals("1,2,3,4,5", Iterators.toString(iterator, ","));
    }

    @DisplayName("map() PreConditionCheck1 / Integer iterator Function param null")
    @Test
    void mapNullCheck1() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.map(intIterator1, null));
    }

    @DisplayName("map() PreConditionCheck2 / Double iterator Function param null")
    @Test
    void mapNullCheck2() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.map(doubleIterator1, null));
    }

    @DisplayName("map() PreConditionCheck3 / Integer InfiniteIterator Function param null")
    @Order(2)
    @Test
    void mapNullCheck3() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.map(infiniteIntIterator1, null));
    }

    @DisplayName("map() PreConditionCheck4 / Double InfiniteIterator Function param null")
    @Order(2)
    @Test
    void mapNullCheck4() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.map(infiniteIntIterator1, null));
    }

    @DisplayName("map() PostConditionCheck1 / Integer Iterator")
    @Test
    void mapPostCondtionTest1() {
        Iterator<Integer> mappingIterator = map(intIterator1, x -> x * 10);
        Iterator<Integer> actualIterator = Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100).iterator();
        while (mappingIterator.hasNext()) {
            assertEquals(mappingIterator.next(), actualIterator.next());
        }
    }

    @DisplayName("map() PostConditionCheck2 / Double Iterator")
    @Test
    void mapPostCondtionTest2() {
        Iterator<Double> mappingIterator = map(intIterator1, x -> x * 10d);
        Iterator<Double> actualIterator = Arrays.asList(10d, 20d, 30d, 40d, 50d, 60d, 70d, 80d, 90d, 100d).iterator();
        while (mappingIterator.hasNext()) {
            assertEquals(mappingIterator.next(), actualIterator.next());
        }
    }

    @DisplayName("map() PostConditionCheck3 / Double Iterator")
    @Order(2)
    @Test
    void mapPostCondtionTest3() {
        InfiniteIterator<Integer> mappingIterator = map(infiniteIntIterator1, x -> x * 10);
        InfiniteIterator<Integer> actualIterator = iterate(10, x -> x + 10);
        int count = 0;
        while (mappingIterator.hasNext() && count < INFINITE_MAX_TEST) {
            assertEquals(mappingIterator.next(), actualIterator.next());
            count++;
        }
    }

    @DisplayName("map() PostConditionCheck4 / Double Infinite Iterator")
    @Order(2)
    @Test
    void mapPostCondtionTest4() {
        InfiniteIterator<Double> mappingIterator = map(infiniteIntIterator1, x -> x * 10d);
        InfiniteIterator<Double> actualIterator = iterate(10d, x -> x + 10);
        for (int i = 0; i < 10; i++) {
            assertEquals(mappingIterator.next(), actualIterator.next());
        }
    }

    @DisplayName("filter() PreCondition null check 1 / iterator Integer")
    @Order(2)
    @Test
    void filterPrecondtionCheck1() {
        assertThrows(IllegalArgumentException.class, () -> filter(intIterator1, null));
    }


    @DisplayName("filter() PreCondition null check 2 / InfiniteIterator")
    @Order(2)
    @Test
    void filterPrecondtionCheck2() {
        assertThrows(IllegalArgumentException.class, () -> filter(infiniteIntIterator1, null));
    }

    @DisplayName("filter() PostCondition / Iterator")
    @Order(2)
    @Test
    void filterPostCondtionTest() {
        Iterator<Integer> filteringIterator = Iterators.filter(intIterator1, x -> x % 2 == 0);
        Iterator<Integer> actualIterator = Arrays.asList(2, 4, 6, 8, 10).iterator();
        while (filteringIterator.hasNext()) {
            assertEquals(filteringIterator.next(), actualIterator.next());
        }
    }

    @DisplayName("filter() PostCondition1 / DoubleIterater")
    @Order(2)
    @Test
    void filterPostCondtionTest1() {
        Iterator<Double> filteringIterator = Iterators.filter(doubleIterator1, x -> x % 2 == 0);
        Iterator<Double> actualIterator = Arrays.asList(2d, 4d, 6d, 8d, 10d).iterator();
        while (filteringIterator.hasNext()) {
            assertEquals(filteringIterator.next(), actualIterator.next());
        }
    }

    // filter 를 사용하기 위해 findfirst 함수를 사용, findfirst를 먼저 testing 하고 하는것이 맞는가?
    // TestMethodOrder로 순서지정으로 해결했음
    @DisplayName("filter() PostCondition2 / IntegerInfiniteIterater")
    @Order(2)
    @Test
    void filterPostCondtionTest2() {
        InfiniteIterator<Double> filteringIterator = Iterators.filter(infiniteDoubleIterator2, x -> x % 2 == 0);
        InfiniteIterator<Double> actualIterator = iterate(2d, x -> x + 2);
        long count = 0;
        while (filteringIterator.hasNext() && count < INFINITE_MAX_TEST) {
            assertEquals(filteringIterator.next(), actualIterator.next());
            count++;
        }
    }

    @DisplayName("findFirst() null check 1 / if param1 null")
    @Order(1)
    @Test
    void findFirstNullCheck() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.findFirst(intIterator1, null));
    }

    @DisplayName("findFirst() null check 2 / if param2 null")
    @Order(1)
    @Test
    void findFirstNullCheck2() {
        Iterator<Integer> nullIterator = null;
        assertThrows(IllegalArgumentException.class, () -> Iterators.findFirst(nullIterator, (x) -> true));
    }

    @DisplayName("findFirst() null check 3 / if params both null")
    @Order(1)
    @Test
    void findFirstNullCheck3() {
        Iterator<Integer> nullIterator = null;
        assertThrows(IllegalArgumentException.class, () -> Iterators.findFirst(nullIterator, null));
    }

    @DisplayName("findFirst() PostCondtion check 1 / Iterator")
    @Order(1)
    @Test
    void findFirstPostConditionTest() {
        for (int i = 1; i < 11; i++) {
            assertEquals(i, findFirst(intIterator1, (x) -> true));
        }
    }

    @DisplayName("findFirst() PostCondtion check 2 / InfiniteIterator")
    @Order(1)
    @Test
    void findFirstPostConditionTest2() {
        for (int i = 1; i < 11; i++) {
            assertEquals(i, findFirst(infiniteIntIterator1, x -> true));
        }
    }

    @DisplayName("iterate() null check 1")
    @Order(1)
    @Test
    void iterateNullCheck1() {
        assertThrows(IllegalArgumentException.class, () -> iterate(null, (x) -> x));
    }

    @DisplayName("iterate() null check 2")
    @Order(1)
    @Test
    void iterateNullCheck2() {
        assertThrows(IllegalArgumentException.class, () -> iterate(0, null));
    }

    @DisplayName("iterate() PostCondition 1")
    @Order(1)
    @Test
    void iteratePostCondtionTest() {
        InfiniteIterator<Integer> iterator = iterate(1, x -> x + 1);
        for (int i = 0; i < 100; i++) {
            assertEquals(iterator.next(), infiniteIntIterator1.next());
        }
    }

    @DisplayName("limit() null check 1 / Iterator")
    @Test
    void limitNullCheckTest1() {
        Iterator<Integer> iterator = null;
        assertThrows(IllegalArgumentException.class, () -> Iterators.limit(iterator, 3));
    }

    @DisplayName("limit() null check 2 / InfiniteIterator")
    @Test
    void limitNullCheckTest2() {
        InfiniteIterator<Integer> infiniteIterator = null;
        assertThrows(IllegalArgumentException.class, () -> Iterators.limit(infiniteIterator, 3));
    }

    @DisplayName("limit() PreCondition check 3 / if maxSize negative")
    @Test
    void limitNullCheckTest3() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.limit(intIterator1, -3));
    }

    @DisplayName("limit() PreCondition check 4 / if maxSize negative in InfiniteIterator")
    @Test
    void limitNullCheckTest4() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.limit(infiniteIntIterator1, -3));
    }

    @DisplayName("limit() PreCondition Check 5 / if maxSize bigger than Iterator size")
    @Test
    void limitNullCheckTest5() {
        // nothing thrown
        Iterators.limit(intIterator1, 15);
    }

    @DisplayName("limit() PostCondition Check 1 / normal situation / Comsump all tokens next() situation")
    @Test
    void limitPostConditionCheck1() {
        long maxSize = 3;
        Iterator<Integer> limit3Iterator = Iterators.limit(intIterator1, maxSize);
        for (int i = 1; i <= maxSize; i++) {
            assertEquals(limit3Iterator.next(), i);
        }
        assertEquals(null, limit3Iterator.next());
    }

    @DisplayName("limit() PostCondition Check 2 / normal situation / Comsump all tokens hasNext() situation")
    @Test
    void limitPostConditionCheck2() {
        long maxSize = 3;
        Iterator<Integer> limit3Iterator = Iterators.limit(intIterator1, maxSize);
        for (int i = 1; i <= maxSize; i++) {
            assertEquals(limit3Iterator.next(), i);
        }
        assertFalse(limit3Iterator.hasNext());
    }

    @DisplayName("limit() PostCondition Check 3 / normal situation in InfiniteIterator/ Comsump all tokens next() situation")
    @Test
    void limitPostConditionCheck3() {
        long maxSize = 3;
        Iterator<Integer> limit3Iterator = Iterators.limit(infiniteIntIterator1, maxSize);
        for (int i = 1; i <= maxSize; i++) {
            assertEquals(limit3Iterator.next(), i);
        }
        assertEquals(null, limit3Iterator.next());
    }

    @DisplayName("limit() PostCondition Check 4 / normal situation in InfiniteIterator/ Comsump all tokens hasNext() situation")
    @Test
    void limitPostConditionCheck4() {
        long maxSize = 3;
        Iterator<Integer> limit3Iterator = Iterators.limit(infiniteIntIterator1, maxSize);
        for (int i = 1; i <= maxSize; i++) {
            assertEquals(limit3Iterator.next(), i);
        }
        assertFalse(limit3Iterator.hasNext());
    }

    @DisplayName("generate() null check 1 / param supplier null check")
    @Test
    void generatePreConditionCheck() {
        assertThrows(IllegalArgumentException.class, () -> generate(null));
    }

    // Supplier test
    static int x = 0;

    static int createNumber() {
        return x + 1;
    }

    @DisplayName("generate() PostCondition Check1 / ")
    @Test
    void generatePostConditionTest() {
        infiniteIntIterator1 = generate(IteratorsTest::createNumber);
        for (int i = 1; i < INFINITE_MAX_TEST; i++) {
            assertEquals(i, infiniteDoubleIterator1.next());
        }
    }

    @DisplayName("zip() null check 1 / params Iterator,Iterator")
    @Test
    void zipNullCheck1() {
        assertThrows(IllegalArgumentException.class, () -> zip(null, intIterator1, intIterator2));
        intIterator1 = null;
        assertThrows(IllegalArgumentException.class, () -> zip((x, y) -> x, intIterator1, intIterator2));
        assertThrows(IllegalArgumentException.class, () -> zip((x, y) -> x, intIterator2, intIterator1));
        intIterator2 = null;
        assertThrows(IllegalArgumentException.class, () -> zip((x, y) -> x, intIterator1, intIterator2));
    }

    @DisplayName("zip() null check 2 / Overloading zip() to use InfiniteIterator")
    @Test
    void zipNullCheck2() {
        assertThrows(IllegalArgumentException.class, () -> zip(null, infiniteIntIterator2, infiniteIntIterator2));
        infiniteIntIterator1 = null;
        assertThrows(IllegalArgumentException.class, () -> zip((x, y) -> x, infiniteIntIterator1, infiniteIntIterator1));
        assertThrows(IllegalArgumentException.class, () -> zip((x, y) -> x, infiniteIntIterator1, infiniteIntIterator1));
        infiniteIntIterator1 = null;
        assertThrows(IllegalArgumentException.class, () -> zip((x, y) -> x, infiniteIntIterator1, infiniteIntIterator1));
    }

    //Iterators hasNext() must be false after zipping
    @DisplayName("zip() postCondition / params Iterator, Iterator")
    @Test
    void zipPostConditionTest1() {
        Iterator<Integer> zipIterator = zip((x, y) -> Math.addExact(x, y), intIterator1, intIterator2);
        for (int i = 1; i <= 10; i++) {
            assertEquals(i * 2, zipIterator.next());
        }
        assertFalse(zipIterator.hasNext());
        assertFalse(intIterator1.hasNext());
        assertFalse(intIterator2.hasNext());
    }

    // Iterators hasNext() must be false after zipping
    @DisplayName("zip() postCondition 3/ params InfiniteIterator, Iterator")
    @Test
    void zipPostConditionTest3() {
        Iterator<Integer> zipIterator = zip((x, y) -> Math.addExact(x, y), infiniteIntIterator1, intIterator2);
        for (int i = 1; i <= 10; i++) {
            assertEquals(i * 2, zipIterator.next());
        }
        assertFalse(zipIterator.hasNext());
        assertFalse(intIterator2.hasNext());
    }

    @DisplayName("zip() postCondition 4/ params Iterator, InfiniteIterator")
    @Test
    void zipPostConditionTest4() {
        Iterator<Integer> zipIterator = zip((x, y) -> Math.addExact(x, y), intIterator1, infiniteIntIterator2);
        for (int i = 1; i <= 10; i++) {
            assertEquals(i * 2, zipIterator.next());
        }
        assertFalse(zipIterator.hasNext());
        assertFalse(intIterator1.hasNext());
    }

    @DisplayName("zip() postCondition 5/ params InfiniteIterator, InfiniteIterator")
    @Test
    void zipPostConditionTest5() {
        InfiniteIterator<Integer> zipIterator = zip(Math::addExact, infiniteIntIterator1, infiniteIntIterator2);
        for (int i = 1; i <= INFINITE_MAX_TEST; i++) {
            assertEquals(i * 2, zipIterator.next());
        }
    }

    @DisplayName("count() null check / Iterator null check")
    @Test
    void countNullCheck1() {
        intIterator1 = null;
        assertThrows(IllegalArgumentException.class, () -> count(intIterator1));
    }

    @DisplayName("count() null check 1/ InfiniteIterator null check")
    @Test
    void countNullCheck2() {
        infiniteIntIterator1 = null;
        assertThrows(IllegalArgumentException.class, () -> count(infiniteIntIterator1));
    }

    @DisplayName("count() PostCondition 1 / Iterator")
    @Test
    void countPreConditionCheck() {
        assertEquals(10L, count(intIterator1));
    }

    @DisplayName("count() PostCondition 2 / InfiniteInterator")
    @Test
    void countPreConditionCheck1() {
        assertEquals(Long.MAX_VALUE, count(infiniteIntIterator1));
    }

    @DisplayName("count() PostCondition 1 / Interator hasNext (always true) overloading")
    @Test
    void countPreConditionCheck2() {
        Iterator<Integer> iterator = new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                return 1;
            }
        };
        assertEquals(Long.MAX_VALUE, count(iterator));
    }

    @DisplayName("get() null check 1 / Iterator")
    @Test
    void getNullCheck1() {
        intIterator1 = null;
        assertThrows(IllegalArgumentException.class, () -> Iterators.get(intIterator1, 3));
    }

    @DisplayName("get() null check 2 / Iterator")
    @Test
    void getNullCheck2() {
        infiniteIntIterator1 = null;
        assertThrows(IllegalArgumentException.class, () -> Iterators.get(infiniteIntIterator1, 3));
    }

    @DisplayName("get() PreCondition / idx negative iterator")
    @Test
    void getPreConditionCheck() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.get(intIterator1, -4));
    }

    @DisplayName("get() PreCondition 1 / idx negative infiniteIterator")
    @Test
    void getPreConditionCheck1() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.get(infiniteIntIterator1, -4));
    }

    @DisplayName("get() PostCondition 1/ Iterator")
    @Test
    void getPostCondition1() {
        assertEquals(4, Iterators.get(intIterator1, 3));
    }

    @DisplayName("get() PostCondition 2/ InfiniteIterator")
    @Test
    void getPostCondition2() {
        assertEquals(4, Iterators.get(infiniteIntIterator1, 3));
    }

    @DisplayName("getLast() nullcheck 1 / iterator")
    @Test
    void getLastNullcheck() {
        intIterator1 = null;
        assertThrows(IllegalArgumentException.class, () -> Iterators.getLast(intIterator1));
    }

    @DisplayName("getLast() postCondition Test/ iterator")
    @Test
    void getLastPostCondition() {
        assertEquals(10, Iterators.getLast(intIterator1));
    }

    @DisplayName("toList() null check")
    @Test
    void tolistNullCheck() {
        intIterator1 = null;
        assertThrows(IllegalArgumentException.class, () -> Iterators.toList(intIterator1));
    }

    @DisplayName("toList() postCondition Check")
    @Test
    void tolistPostConditionTest() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> toListResult = Iterators.toList(intIterator1);
        for (int i = 0; i < 10; i++) {
            assertEquals(list.get(i), toListResult.get(i));
        }
    }

    @BeforeEach
    @DisplayName("Variable initialization")
    void variableInitial() {
        intIterator1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).iterator();
        intIterator2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).iterator();
        doubleIterator1 = Arrays.asList(1d, 2d, 3d, 4d, 5d, 6d, 7d, 8d, 9d, 10d).iterator();
        doubleIterator2 = Arrays.asList(1d, 2d, 3d, 4d, 5d, 6d, 7d, 8d, 9d, 10d).iterator();
        infiniteIntIterator1 = iterate(1, x -> x + 1);
        infiniteIntIterator2 = iterate(1, x -> x + 1);
        infiniteDoubleIterator1 = iterate(1d, x -> x + 1);
        infiniteDoubleIterator2 = iterate(1d, x -> x + 1);
    }
}
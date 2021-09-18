package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.RepeatedTest;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListSpliteratorExampleTest {

    LinkedListSpliteratorExample linkedListSpliteratorExample = new LinkedListSpliteratorExample();

    @RepeatedTest(5)
    void multipleEachValue() {
        // given
        int size = 1000000;
        LinkedList<Integer> intList = DataSet.generateIntegerLinkedList(size);

        // when
        List<Integer> result = linkedListSpliteratorExample.multipleEachValue(intList, 2, false);

        // then
        assertEquals(size, result.size());
    }

    @RepeatedTest(5)
    void multipleEachValueParallel() {
        // given
        int size = 1000000;
        LinkedList<Integer> intList = DataSet.generateIntegerLinkedList(size);

        // when
        List<Integer> result = linkedListSpliteratorExample.multipleEachValue(intList, 2, true);

        // then
        assertEquals(size, result.size());
    }
}
package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListSpliteratorExampleTest {

    ArrayListSpliteratorExample arrayListSpliteratorExample = new ArrayListSpliteratorExample();

    @RepeatedTest(5)
    void multipleEachValue() {
        // given
        int size = 1000000;
        ArrayList<Integer> intList = DataSet.generateArrayList(size);

        // when
        List<Integer> result = arrayListSpliteratorExample.multipleEachValue(intList, 2, false);

        // then
        assertEquals(size, result.size());
    }

    @RepeatedTest(5)
    void multipleEachValueParallel() {
        // given
        int size = 1000000;
        ArrayList<Integer> intList = DataSet.generateArrayList(size);

        // when
        List<Integer> result = arrayListSpliteratorExample.multipleEachValue(intList, 2, true);

        // then
        assertEquals(size, result.size());
    }
}
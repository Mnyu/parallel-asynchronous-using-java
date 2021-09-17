package com.learnjava.parallelstreams;

import com.learnjava.util.CommonUtil;
import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParallelStreamsExampleTest {

    ParallelStreamsExample parallelStreamsExample = new ParallelStreamsExample();

    @Test
    void transformStrings() {
        // Given
        List<String> inputList = DataSet.namesList();

        // When
        CommonUtil.startTimer();
        List<String> resultList = parallelStreamsExample.transformStrings(inputList);
        CommonUtil.timeTaken();

        // Then
        assertEquals(4, resultList.size());
        resultList.forEach(name -> assertTrue(name.contains("-")));
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void transformStrings2(boolean isParallel) {
        // Given
        List<String> inputList = DataSet.namesList();

        // When
        CommonUtil.stopWatchReset();
        CommonUtil.startTimer();
        List<String> resultList = parallelStreamsExample.transformStrings2(inputList, isParallel);
        CommonUtil.timeTaken();

        // Then
        assertEquals(4, resultList.size());
        resultList.forEach(name -> assertTrue(name.contains("-")));
    }
}
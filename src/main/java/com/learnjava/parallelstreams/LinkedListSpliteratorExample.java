package com.learnjava.parallelstreams;

import com.learnjava.util.CommonUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LinkedListSpliteratorExample {

    public List<Integer> multipleEachValue(LinkedList<Integer> inputList, int multiplyValue, boolean isParallel) {

        CommonUtil.stopWatchReset();
        CommonUtil.startTimer();
        Stream<Integer> intStream = inputList.stream();  // SEQUENTIAL STREAM

        if(isParallel) {
            intStream.parallel(); // TURN SEQUENTIAL STREAM TO PARALLEL STREAM
        }

        List<Integer> resultList = intStream.map(num -> num * multiplyValue)
                .collect(Collectors.toList());

        CommonUtil.timeTaken();
        return resultList;

    }
}

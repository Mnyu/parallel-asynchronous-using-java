package com.learnjava.parallelstreams;

import com.learnjava.util.LoggerUtil;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ParallelStreamResultOrder {

    public static List<Integer> listOrder(List<Integer> inputList) {
        return inputList.parallelStream()
                .map(integer -> integer * 2)
                .collect(Collectors.toList());
    }

    public static Set<Integer> setOrder(Set<Integer> inputList) {
        return inputList.parallelStream()
                .map(integer -> integer * 2)
                .collect(Collectors.toSet());
    }

    public static void main(String[] args) {
//        List<Integer> inputList = List.of(1, 2, 3, 4, 5, 6, 7, 8);
//        LoggerUtil.log("Input List : " + inputList);
//        List<Integer> resultList = listOrder(inputList);
//        LoggerUtil.log("Output List : " + resultList);

        Set<Integer> inputList = Set.of(1, 2, 3, 4, 5, 6, 7, 8);
        LoggerUtil.log("Input Set : " + inputList);
        Set<Integer> resultList = setOrder(inputList);
        LoggerUtil.log("Output Set : " + resultList);


    }

}

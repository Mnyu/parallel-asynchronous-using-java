package com.learnjava.parallelstreams;

import com.learnjava.util.CommonUtil;
import com.learnjava.util.DataSet;
import com.learnjava.util.LoggerUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.learnjava.util.CommonUtil.delay;

public class ParallelStreamsExample {

    private String addNameLengthTransform(String name) {
        delay(500);
        return name.length()+" - "+name ;
    }

    public List<String> transformStrings(List<String> namesList) {
        return namesList
            //.stream() // SEQUENTIAL PROCESSING - stream() - by default
            .parallelStream() // PARALLEL PROCESSING
            .map(this::addNameLengthTransform)
            // .sequential() // sequential function
            .parallel()
            .collect(Collectors.toList());
    }

    public List<String> transformStrings1(List<String> namesList) {
        return namesList
                .parallelStream() // PARALLEL PROCESSING
                .map(this::addNameLengthTransform)
                .sequential() // sequential function
                .parallel() // again parallel - this will override the previous sequential()
                .collect(Collectors.toList());
    }

    public List<String> transformStrings2(List<String> namesList, boolean isParallel) {
        Stream<String> namesStream = namesList.stream();
        if(isParallel) {
            namesStream.parallel();
        }
        return namesStream
                .map(this::addNameLengthTransform)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> namesList = DataSet.namesList();
        ParallelStreamsExample obj = new ParallelStreamsExample();

        CommonUtil.startTimer();

        List<String> resultList = obj.transformStrings(namesList);
        LoggerUtil.log("resultList : " + resultList);

        CommonUtil.timeTaken();
    }
}

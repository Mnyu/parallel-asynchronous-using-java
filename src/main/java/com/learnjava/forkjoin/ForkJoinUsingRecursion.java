package com.learnjava.forkjoin;

import com.learnjava.util.DataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import static com.learnjava.util.CommonUtil.delay;
import static com.learnjava.util.CommonUtil.stopWatch;
import static com.learnjava.util.LoggerUtil.log;

public class ForkJoinUsingRecursion extends RecursiveTask<List<String>> {

    private List<String> inputList;

    public ForkJoinUsingRecursion(List<String> inputList) {
        this.inputList = inputList;
    }

    private static String addNameLengthTransform(String name) {
        delay(500);
        return name.length()+" - "+name ;
    }

    @Override
    protected List<String> compute() {
        // Compute method is where the fork and join operations will happen.

        if(inputList.size() <= 1) {
            List<String> resultList = new ArrayList<>();
            inputList.forEach(name -> resultList.add(addNameLengthTransform(name))); // COMPUTATION STEP
            return resultList;
        }

        // Split the input data into chunks
        int mid = inputList.size() / 2;

        ForkJoinTask<List<String>> leftInputList = new ForkJoinUsingRecursion(inputList.subList(0, mid)).fork(); // FORKING STEP
        // the above forking step adds the subtask into the internal queue/deque of threads of fork-join framework for other threads to pick up.

        inputList = inputList.subList(mid, inputList.size());
        List<String> rightResult = compute(); // this is where recursion happens - RECURSION STEP

        // JOINING STEP
        List<String> leftResult = leftInputList.join(); // join
        leftResult.addAll(rightResult);
        return leftResult;

        // NOTE : the fork() join() calls are non-blocking - it will push the tasks on worker threads queues asynchronously.
    }

    public static void main(String[] args) {

        stopWatch.start();
        List<String> names = DataSet.namesList();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinUsingRecursion forkJoinUsingRecursion = new ForkJoinUsingRecursion(names);
        List<String> resultList = forkJoinPool.invoke(forkJoinUsingRecursion); // Actual Input gets added to the shared queue.

        stopWatch.stop();
        log("Final Result : "+ resultList);
        log("Total Time Taken : "+ stopWatch.getTime());
    }

}

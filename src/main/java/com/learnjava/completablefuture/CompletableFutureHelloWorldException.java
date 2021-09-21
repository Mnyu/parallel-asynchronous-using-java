package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import com.learnjava.util.CommonUtil;
import com.learnjava.util.LoggerUtil;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureHelloWorldException {

    private HelloWorldService hws;

    public CompletableFutureHelloWorldException(HelloWorldService hws) {
        this.hws = hws;
    }

    public String helloWorld_3_async_calls_handle() {
        CommonUtil.stopWatchReset();
        CommonUtil.startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        CompletableFuture<String> hi = CompletableFuture.supplyAsync(() -> {
            CommonUtil.delay(1000);
            return " Hi Completable future!!!";
        });
        String hw =  hello
                .handle((prevResult, ex) -> {
                    LoggerUtil.log("prev result : " + prevResult);
                    if(ex != null) {
                        LoggerUtil.log("Exception for HELLO is : " + ex.getMessage());
                        return "";
                    }
                    return prevResult;
                })
                .thenCombine(world, (h, w) -> h + w)
                .handle((prevResult, ex) -> {
                    LoggerUtil.log("prev result : " + prevResult);
                    if(ex != null) {
                        LoggerUtil.log("Exception for WORLD is : " + ex.getMessage());
                        return "";
                    }
                    return prevResult;
                })
                .thenCombine(hi, (prev, curr) -> prev + curr)
                .thenApply(String::toUpperCase)
                .join();
        CommonUtil.timeTaken();
        return hw;
    }

    public String helloWorld_3_async_calls_exceptionally() {
        CommonUtil.stopWatchReset();
        CommonUtil.startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        CompletableFuture<String> hi = CompletableFuture.supplyAsync(() -> {
            CommonUtil.delay(1000);
            return " Hi Completable future!!!";
        });
        String hw =  hello
                .exceptionally(ex -> {
                    LoggerUtil.log("Exception for HELLO is : " + ex.getMessage());
                    return "";
                })
                .thenCombine(world, (h, w) -> h + w)
                .exceptionally(ex -> {
                    LoggerUtil.log("Exception for WORLD is : " + ex.getMessage());
                    return "";
                })
                .thenCombine(hi, (prev, curr) -> prev + curr)
                .thenApply(String::toUpperCase)
                .join();
        CommonUtil.timeTaken();
        return hw;
    }

    public String helloWorld_3_async_calls_whenComplete() {
        CommonUtil.stopWatchReset();
        CommonUtil.startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        CompletableFuture<String> hi = CompletableFuture.supplyAsync(() -> {
            CommonUtil.delay(1000);
            return " Hi Completable future!!!";
        });
        String hw =  hello
                .whenComplete((prevResult, ex) -> {
                    LoggerUtil.log("prev result : " + prevResult);
                    if(ex != null) {
                        LoggerUtil.log("Exception for HELLO is : " + ex.getMessage());
                    }
                })
                .thenCombine(world, (h, w) -> h + w)
                .whenComplete((prevResult, ex) -> {
                    LoggerUtil.log("prev result : " + prevResult);
                    if(ex != null) {
                        LoggerUtil.log("Exception for WORLD is : " + ex.getMessage());
                    }
                })
                .thenCombine(hi, (prev, curr) -> prev + curr)
                .thenApply(String::toUpperCase)
                .join();
        CommonUtil.timeTaken();
        return hw;
    }


}

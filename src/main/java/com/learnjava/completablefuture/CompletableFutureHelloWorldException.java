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
                    if(ex != null) {
                        LoggerUtil.log("Exception for HELLO is : " + ex.getMessage());
                        return "";
                    }
                    return prevResult;
                })
                .thenCombine(world, (h, w) -> h + w)
                .handle((prevResult, ex) -> {
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

}

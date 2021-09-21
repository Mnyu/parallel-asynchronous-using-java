package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import com.learnjava.util.CommonUtil;
import com.learnjava.util.LoggerUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureHelloWorld {

    private HelloWorldService hws;

    public CompletableFutureHelloWorld(HelloWorldService hws) {
        this.hws = hws;
    }

    public static void example1() {
        HelloWorldService hws = new HelloWorldService();
        CompletableFuture.supplyAsync(() -> hws.helloWorld())
                .thenAccept(result -> {
                    LoggerUtil.log("Result is : " + result);
                })
                .join();
        LoggerUtil.log("Done!");
        // CommonUtil.delay(2000);
    }

    public static void example2() {
        HelloWorldService hws = new HelloWorldService();
        CompletableFuture.supplyAsync(() -> hws.helloWorld())
                .thenApply(result -> result.toUpperCase())
                .thenAccept(result -> {
                    LoggerUtil.log("Result is : " + result);
                })
                .join();
        LoggerUtil.log("Done!");
        // CommonUtil.delay(2000);
    }

    public CompletableFuture<String> helloWorld() {
        return CompletableFuture.supplyAsync(() -> hws.helloWorld())
                .thenApply(result -> result.toUpperCase());
    }

    public String helloWorld_conventional() {
        String hello = hws.hello();
        String world = hws.world();
        return hello + world;
    }

    public String helloWorld_multiple_async_calls() {
        CommonUtil.stopWatchReset();
        CommonUtil.startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        String hw =  hello.thenCombine(world, (h, w) -> h + w)
                .thenApply(String::toUpperCase)
                .join();
        CommonUtil.timeTaken();
        return hw;
    }

    public String helloWorld_3_async_calls() {
        CommonUtil.stopWatchReset();
        CommonUtil.startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        CompletableFuture<String> hi = CompletableFuture.supplyAsync(() -> {
            CommonUtil.delay(1000);
            return " Hi Completable future!!!";
        });
        String hw =  hello
                .thenCombine(world, (h, w) -> h + w)
                .thenCombine(hi, (prev, curr) -> prev + curr)
                .thenApply(String::toUpperCase)
                .join();
        CommonUtil.timeTaken();
        return hw;
    }

    public String helloWorld_3_async_calls_logging() {
        // The logging is added to see which threads execute thenCombine and
        // thenApply in the CompletableFuture pipeline.
        CommonUtil.stopWatchReset();
        CommonUtil.startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        CompletableFuture<String> hi = CompletableFuture.supplyAsync(() -> {
            CommonUtil.delay(1000);
            return " Hi Completable future!!!";
        });
        String hw =  hello
                .thenCombine(world, (h, w) -> {
                    LoggerUtil.log("thenCombine hello-world");
                    return h + w;
                })
                .thenCombine(hi, (prev, curr) -> {
                    LoggerUtil.log("thenCombine prev-curr");
                    return prev + curr;
                })
                .thenApply(s -> {
                    LoggerUtil.log("thenApply string to uppercase");
                    return s.toUpperCase();
                })
                .join();
        CommonUtil.timeTaken();
        return hw;
    }

    public String helloWorld_3_async_calls_custom_thread_pool() {
        CommonUtil.stopWatchReset();
        CommonUtil.startTimer();

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello(), executorService);
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world(), executorService);
        CompletableFuture<String> hi = CompletableFuture.supplyAsync(() -> {
            CommonUtil.delay(1000);
            return " Hi Completable future!!!";
        }, executorService);
        String hw =  hello
                .thenCombine(world, (h, w) -> {
                    LoggerUtil.log("thenCombine hello-world");
                    return h + w;
                })
                .thenCombine(hi, (prev, curr) -> {
                    LoggerUtil.log("thenCombine prev-curr");
                    return prev + curr;
                })
                .thenApply(s -> {
                    LoggerUtil.log("thenApply string to uppercase");
                    return s.toUpperCase();
                })
                .join();
        CommonUtil.timeTaken();
        return hw;
    }

    public CompletableFuture<String> helloWorld_thenCompose() {
        return CompletableFuture.supplyAsync(() -> hws.hello())
                .thenCompose(prevResult -> hws.worldFuture(prevResult))
                .thenApply(String::toUpperCase);
    }

    public static void main(String[] args) {
        // example1();
        //example2();
    }
}

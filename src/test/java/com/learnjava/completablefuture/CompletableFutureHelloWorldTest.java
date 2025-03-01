package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import com.learnjava.util.CommonUtil;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

class CompletableFutureHelloWorldTest {

    HelloWorldService hws = new HelloWorldService();
    CompletableFutureHelloWorld cfhw = new CompletableFutureHelloWorld(hws);

    @Test
    void helloWorld() {
        // given

        // when
        CompletableFuture<String> completableFuture = cfhw.helloWorld();

        // then
        completableFuture.thenAccept(s -> {
            assertEquals("HELLO WORLD", s);
        })
        .join();
    }

    @Test
    void helloWorld_multiple_sync_calls() {
        // given

        // when
        String helloWorld = cfhw.helloWorld_multiple_async_calls();

        // then
        assertEquals("HELLO WORLD!", helloWorld);
    }

    @Test
    void helloWorld_3_async_calls() {
        // given

        // when
        String helloWorld = cfhw.helloWorld_3_async_calls();

        // then
        assertEquals("HELLO WORLD! HI COMPLETABLE FUTURE!!!", helloWorld);
    }

    @Test
    void helloWorld_3_async_calls_logging() {
        // given

        // when
        String helloWorld = cfhw.helloWorld_3_async_calls_logging();

        // then
        assertEquals("HELLO WORLD! HI COMPLETABLE FUTURE!!!", helloWorld);
    }

    @Test
    void helloWorld_3_async_calls_customThreadPool() {
        // given

        // when
        String helloWorld = cfhw.helloWorld_3_async_calls_custom_thread_pool();

        // then
        assertEquals("HELLO WORLD! HI COMPLETABLE FUTURE!!!", helloWorld);
    }

    @Test
    void helloWorld_thenCompose() {
        // given
        CommonUtil.stopWatchReset();
        CommonUtil.startTimer();
        // when
        CompletableFuture<String> completableFuture = cfhw.helloWorld_thenCompose();

        // then
        completableFuture.thenAccept(s -> {
                    assertEquals("HELLO WORLD!", s);
                })
                .join();
        CommonUtil.timeTaken();
    }

    @Test
    void anyOf() {
        // given

        // when
        String hw = cfhw.anyOf();

        // then
        assertEquals("hello world", hw);
    }
}
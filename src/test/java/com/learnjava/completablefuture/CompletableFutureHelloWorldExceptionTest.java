package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class CompletableFutureHelloWorldExceptionTest {

    @Mock
    HelloWorldService helloWorldService = Mockito.mock(HelloWorldService.class);

    @InjectMocks
    CompletableFutureHelloWorldException hwcfe;

    @Test
    void helloWorld_3_async_calls_handle() {
        // given
        Mockito.when(helloWorldService.hello()).thenThrow(new RuntimeException("Trigger exception"));
        Mockito.when(helloWorldService.world()).thenCallRealMethod();

        // when
        String result = hwcfe.helloWorld_3_async_calls_handle();

        // then
        assertEquals(" WORLD! HI COMPLETABLE FUTURE!!!", result);
    }

    @Test
    void helloWorld_3_async_calls_handle2() {
        // given
        Mockito.when(helloWorldService.hello()).thenThrow(new RuntimeException("Trigger HELLO exception"));
        Mockito.when(helloWorldService.world()).thenThrow(new RuntimeException("Trigger WORLD exception"));

        // when
        String result = hwcfe.helloWorld_3_async_calls_handle();

        // then
        assertEquals(" HI COMPLETABLE FUTURE!!!", result);
    }

    @Test
    void helloWorld_3_async_calls_handle3() {
        // given
        Mockito.when(helloWorldService.hello()).thenCallRealMethod();
        Mockito.when(helloWorldService.world()).thenCallRealMethod();

        // when
        String result = hwcfe.helloWorld_3_async_calls_handle();

        // then
        assertEquals("HELLO WORLD! HI COMPLETABLE FUTURE!!!", result);
    }

    @Test
    void helloWorld_3_async_calls_exceptionally() {
        // given
        Mockito.when(helloWorldService.hello()).thenCallRealMethod();
        Mockito.when(helloWorldService.world()).thenCallRealMethod();

        // when
        String result = hwcfe.helloWorld_3_async_calls_exceptionally();

        // then
        assertEquals("HELLO WORLD! HI COMPLETABLE FUTURE!!!", result);
    }

    @Test
    void helloWorld_3_async_calls_exceptionally2() {
        // given
        Mockito.when(helloWorldService.hello()).thenThrow(new RuntimeException("Trigger HELLO exception"));
        Mockito.when(helloWorldService.world()).thenThrow(new RuntimeException("Trigger WORLD exception"));

        // when
        String result = hwcfe.helloWorld_3_async_calls_exceptionally();

        // then
        assertEquals(" HI COMPLETABLE FUTURE!!!", result);
    }

    @Test
    void helloWorld_3_async_calls_exceptionally3() {
        // given
        Mockito.when(helloWorldService.hello()).thenThrow(new RuntimeException("Trigger exception"));
        Mockito.when(helloWorldService.world()).thenCallRealMethod();

        // when
        String result = hwcfe.helloWorld_3_async_calls_exceptionally()  ;

        // then
        assertEquals(" WORLD! HI COMPLETABLE FUTURE!!!", result);
    }

    @Test
    void helloWorld_3_async_calls_whenComplete() {
        // given
        Mockito.when(helloWorldService.hello()).thenCallRealMethod();
        Mockito.when(helloWorldService.world()).thenCallRealMethod();

        // when
        String result = hwcfe.helloWorld_3_async_calls_whenComplete();

        // then
        assertEquals("HELLO WORLD! HI COMPLETABLE FUTURE!!!", result);
    }

    // This test case will fail
    @Test
    void helloWorld_3_async_calls_whenComplete2() {
        // given
        Mockito.when(helloWorldService.hello()).thenThrow(new RuntimeException("Trigger HELLO exception"));
        Mockito.when(helloWorldService.world()).thenThrow(new RuntimeException("Trigger WORLD exception"));

        // when
        String result = hwcfe.helloWorld_3_async_calls_whenComplete();

        // then
        assertEquals(" HI COMPLETABLE FUTURE!!!", result);
    }

    // This test case will fail
    @Test
    void helloWorld_3_async_calls_whenComplete3() {
        // given
        Mockito.when(helloWorldService.hello()).thenThrow(new RuntimeException("Trigger exception"));
        Mockito.when(helloWorldService.world()).thenCallRealMethod();

        // when
        String result = hwcfe.helloWorld_3_async_calls_whenComplete()  ;

        // then
        assertEquals(" WORLD! HI COMPLETABLE FUTURE!!!", result);
    }
}
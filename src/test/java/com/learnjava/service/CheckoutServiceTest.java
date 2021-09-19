package com.learnjava.service;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CheckoutResponse;
import com.learnjava.domain.checkout.CheckoutStatus;
import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest {

    PriceValidatorService priceValidatorService = new PriceValidatorService();
    CheckoutService checkoutService = new CheckoutService(priceValidatorService);

    @Test
    void noOfCores() {
        // given

        // when
        System.out.println("No of cores : " + Runtime.getRuntime().availableProcessors());

        // then
    }

    @Test
    void parallelism() {
        // given

        // when
        System.out.println("Parallelism : " + ForkJoinPool.getCommonPoolParallelism());

        // then
    }

    @Test
    void checkout6Items() {
        // given
        Cart cart = DataSet.createCart(6);

        // when
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);

        // then
        assertEquals(CheckoutStatus.SUCCESS, checkoutResponse.getCheckoutStatus());
        assertTrue(checkoutResponse.getFinalRate() > 0);
    }

    @Test
    void checkout17Items() {
        // No of items put here are 17 because my system has 16 cores
        // So the expected result here would be >= 1000 ms
        // Until noOfItems = 16, each item is processed by a thread on a different core, implies time = 500 ms because we gave 500ms as delay for each item.

        // given
        Cart cart = DataSet.createCart(17);

        // when
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);

        // then
        assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
    }

    @Test
    void modifyParallelism() {

        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "100");

        // given
        Cart cart = DataSet.createCart(100);

        // when
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);

        // then
        assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
    }
}
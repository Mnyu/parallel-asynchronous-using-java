package com.learnjava.completablefuture;

import com.learnjava.domain.Product;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import com.learnjava.util.CommonUtil;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceUsingCompletableFutureTest {

    private ProductInfoService pis = new ProductInfoService();
    private ReviewService rs = new ReviewService();

    private ProductServiceUsingCompletableFuture pscf = new ProductServiceUsingCompletableFuture(pis, rs);

    @Test
    void retrieveProductDetails() {
        // given
        String productId = "ABC123";

        // when
        Product product = pscf.retrieveProductDetails(productId);

        // then
        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        assertNotNull(product.getReview());
    }

    @Test
    void retrieveProductDetailsApproach2() {
        // given
        CommonUtil.stopWatchReset();
        CommonUtil.startTimer();
        String productId = "ABC123";

        // when
        CompletableFuture<Product> prCompFuture = pscf.retrieveProductDetailsApproach2(productId);

        // then
        prCompFuture.thenAccept(product -> {
            assertNotNull(product);
            assertTrue(product.getProductInfo().getProductOptions().size() > 0);
            assertNotNull(product.getReview());
        })
        .join();
        CommonUtil.timeTaken();
    }
}
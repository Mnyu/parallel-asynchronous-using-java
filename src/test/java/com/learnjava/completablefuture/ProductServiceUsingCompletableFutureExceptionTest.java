package com.learnjava.completablefuture;

import com.learnjava.domain.Product;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceUsingCompletableFutureExceptionTest {

    @Mock
    private ProductInfoService pisMock;

    @Mock
    private ReviewService rsMock;

    @Mock
    private InventoryService isMock;

    @InjectMocks
    ProductServiceUsingCompletableFuture pscf;

    @Test
    void retrieveProductDetailsWithInventoryApproach2() {
        // Here we will test if exception block is executed or not
        // given
        String productId = "ABC123";
        Mockito.when(pisMock.retrieveProductInfo(Mockito.any())).thenCallRealMethod();
        Mockito.when(rsMock.retrieveReviews(Mockito.any())).thenThrow(new RuntimeException("Exception mocked for reviews retrieval"));
        Mockito.when(isMock.addInventory(Mockito.any())).thenCallRealMethod();

        // when
        Product product = pscf.retrieveProductDetailsWithInventoryApproach2(productId);

        // then
        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        // Inventory Check
        product.getProductInfo().getProductOptions()
                .forEach(productOption -> assertNotNull(productOption.getInventory()));
        assertNotNull(product.getReview());
        assertEquals(0, product.getReview().getNoOfReviews());
    }
}
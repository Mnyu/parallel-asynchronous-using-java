package com.learnjava.service;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CartItem;
import com.learnjava.domain.checkout.CheckoutResponse;
import com.learnjava.domain.checkout.CheckoutStatus;
import com.learnjava.util.CommonUtil;
import com.learnjava.util.LoggerUtil;

import java.util.List;
import java.util.stream.Collectors;

public class CheckoutService {

    private PriceValidatorService priceValidatorService;

    public CheckoutService(PriceValidatorService priceValidatorService) {
        this.priceValidatorService = priceValidatorService;
    }

    public CheckoutResponse checkout(Cart cart) {
        CommonUtil.startTimer();
        List<CartItem> invalidItemsList = cart.getCartItemList()
                //.stream()   // SEQUENTIAL PROCESSING
                .parallelStream()   // ASYNC PROCESSING
                .map(cartItem -> {
                    boolean isPriceInvalid = priceValidatorService.isCartItemInvalid(cartItem);
                    cartItem.setExpired(isPriceInvalid);
                    return cartItem;
                })
                .filter(CartItem::isExpired)
                .collect(Collectors.toList());

        CommonUtil.timeTaken();
        if (invalidItemsList.size() > 0) {
            return new CheckoutResponse(CheckoutStatus.FAILURE, invalidItemsList);
        }

        // Total Price calculation of items in the cart
        //double totalPrice = calculateFinalPrice(cart);
        double totalPrice = calculateFinalPriceReduce(cart);

        LoggerUtil.log("Checkout complete, final price is : " + totalPrice);
        return new CheckoutResponse(CheckoutStatus.SUCCESS, totalPrice);
    }

    private double calculateFinalPrice(Cart cart) {
        return cart.getCartItemList()
                .parallelStream()
                .map(cartItem -> cartItem.getQuantity() * cartItem.getRate())
                .collect(Collectors.summingDouble(Double::doubleValue));
    }

    private double calculateFinalPriceReduce(Cart cart) {
        return cart.getCartItemList()
                .parallelStream()
                .map(cartItem -> cartItem.getQuantity() * cartItem.getRate())
                .reduce(0.0, Double::sum);
    }
}

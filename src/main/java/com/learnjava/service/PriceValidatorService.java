package com.learnjava.service;


import com.learnjava.domain.checkout.CartItem;
import com.learnjava.util.LoggerUtil;

import static com.learnjava.util.CommonUtil.delay;

public class PriceValidatorService {

    public boolean isCartItemInvalid(CartItem cartItem){
        LoggerUtil.log("isCartItemInvalid : " + cartItem);
        int cartId = cartItem.getItemId();
        delay(500);
        if (cartId == 7 || cartId == 9 || cartId == 11) {
            return true;
        }
        return false;
    }
}

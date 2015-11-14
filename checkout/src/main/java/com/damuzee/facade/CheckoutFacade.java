package com.damuzee.facade;

import com.damuzee.common.Checkout;

public interface CheckoutFacade {
    void checkout(String orderId,Checkout ooperation);
    long getIntegral(String userId);
    boolean exchange(long integral,String userId);
}

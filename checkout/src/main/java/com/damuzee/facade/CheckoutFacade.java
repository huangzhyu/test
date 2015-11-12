package com.damuzee.facade;

public interface CheckoutFacade {
    void checkout(String orderId);
    /**
     * 积分兑换
     */
    void exchange(Object obj);
}

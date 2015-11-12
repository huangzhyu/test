package com.damuzee.facade;

import com.damuzee.common.Operation;

public interface CheckoutFacade {
    void checkout(String orderId,Operation ooperation);
}

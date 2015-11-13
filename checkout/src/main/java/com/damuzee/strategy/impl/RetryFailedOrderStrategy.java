package com.damuzee.strategy.impl;

import java.util.List;

import com.damuzee.common.Utils;
import com.damuzee.executor.Executor;
import com.damuzee.model.FailedOrder;
import com.damuzee.model.Member;
import com.damuzee.model.ResultHolder;
import com.damuzee.service.IntegralService;
import com.damuzee.strategy.Strategy;

public class RetryFailedOrderStrategy implements Strategy<FailedOrder> {

    private IntegralService integralService;
    
    private Executor<Member> divideTaskExecutor;
    
    public Executor<Member> getDivideTaskExecutor() {
        return divideTaskExecutor;
    }

    public void setDivideTaskExecutor(Executor<Member> divideTaskExecutor) {
        this.divideTaskExecutor = divideTaskExecutor;
    }

    public IntegralService getIntegralService() {
        return integralService;
    }

    public void setIntegralService(IntegralService integralService) {
        this.integralService = integralService;
    }
    @Override
    public ResultHolder doOperation(FailedOrder order) {
        List<FailedOrder> orders = integralService.getFailedOrder(order);
        if(!Utils.isNull(orders)){
            Member m = null;
            for(FailedOrder pendingOrder : orders){
                m = new Member(pendingOrder.getOrderId(), pendingOrder.getType());
                divideTaskExecutor.submit(m);
            }
        }
        return null;
    }

}

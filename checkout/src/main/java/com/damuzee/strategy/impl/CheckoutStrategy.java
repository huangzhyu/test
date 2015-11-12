package com.damuzee.strategy.impl;

import com.damuzee.model.ResultHolder;
import com.damuzee.service.IntegralService;
import com.damuzee.strategy.Strategy;

public class CheckoutStrategy implements Strategy<ResultHolder> {
	private IntegralService integralService;


	public IntegralService getIntegralService() {
		return integralService;
	}

	public void setIntegralService(IntegralService integralService) {
		this.integralService = integralService;
	}

	@Override
    public ResultHolder doOperation(ResultHolder holder) {
		integralService.add(holder);
        return null;
    }


}
